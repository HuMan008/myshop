package com.syj.myshop.gateway.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * create by GYH on 2023/8/5
 */
@Slf4j
@Configuration
public class RequestLogFilter implements WebFilter {
    //private final List<String> ignoreUrl = List.of("/trading/position", "/futures", "/trading/market");
    private final List<String> ignoreUrl = List.of();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange,  WebFilterChain chain) {
        long startTime = System.currentTimeMillis();
        ServerHttpRequest request = exchange.getRequest();
        // 打印请求路径
        String path = request.getPath().pathWithinApplication().value();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        String requestUrl = UriComponentsBuilder.fromPath(path).queryParams(queryParams).build().toUriString();
        String requestMethod = request.getMethod().name();
        if (ignoreUrl.contains(path) || requestMethod.equals("OPTIONS")) {
            return chain.filter(exchange);
        }
        // 构建成一条长 日志，避免并发下日志错乱
        StringBuilder beforeReqLog = new StringBuilder(300);
        // 日志参数
        List<Object> beforeReqArgs = new ArrayList<>();
        beforeReqLog.append("\n\n================ Request Start  ================\n");
        // 打印路由
        beforeReqLog.append("===> {}: {}\n");
        // 参数
        beforeReqArgs.add(requestMethod);
        beforeReqArgs.add(requestUrl);

        // 打印请求头
        HttpHeaders headers = request.getHeaders();
        headers.forEach((headerName, headerValue) -> {
            beforeReqLog.append("===Headers===  {}: {}\n");
            beforeReqArgs.add(headerName);
            beforeReqArgs.add(String.join(" ", headerValue));
        });

        beforeReqLog.append("================  Request End  =================\n");
        // 打印执行时间
        log.info(beforeReqLog.toString(), beforeReqArgs.toArray());
        BodyCaptureExchange bodyCaptureExchange = new BodyCaptureExchange(exchange);
        return chain.filter(bodyCaptureExchange).doOnEach(it -> {
                    // 构建成一条长 日志，避免并发下日志错乱
                    StringBuilder responseLog = new StringBuilder();
                    // 日志参数
                    List<Object> responseArgs = new ArrayList<>();
                    ServerHttpResponse response = exchange.getResponse();
                    // 打印路由 200 get: /api/xxx/xxx
                    responseLog.append("请求：{}\n响应：{}\n\n<=== {} {}: {}\n");
                    // 参数
                    responseArgs.add(bodyCaptureExchange.getRequest().getFullBody());
                    responseArgs.add(bodyCaptureExchange.getResponse().getFullBody());
                    responseArgs.add(response.getStatusCode().value());
                    responseArgs.add(requestMethod);
                    responseArgs.add(requestUrl);

                    responseLog.append("================  Response End  ================= {}\n");
                    responseArgs.add(System.currentTimeMillis() - startTime + " ms");
                    // 打印执行时间
                    log.info(responseLog.toString(), responseArgs.toArray());
                }
        );
    }

}
