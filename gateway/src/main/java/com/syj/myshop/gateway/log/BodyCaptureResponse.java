package com.syj.myshop.gateway.log;


import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * create by GYH on 2023/12/2
 */
@Slf4j
public class BodyCaptureResponse extends ServerHttpResponseDecorator {

    private StringBuilder body = new StringBuilder();

    public BodyCaptureResponse(ServerHttpResponse delegate) {
        super(delegate);
    }

    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
        if (MediaType.APPLICATION_OCTET_STREAM.equals(getHeaders().getContentType())) {
            log.info("响应文件");
            return super.writeWith(body);
        }
        //if (getDelegate() instanceof SocketServerHttpResponse serverHttpResponse){
        //    this.body = serverHttpResponse.getBodyBuffer();
        //    return super.writeWith(body);
        //}
        if (body instanceof Flux<? extends DataBuffer> buffer) {
            return super.writeWith(buffer.doOnNext(this::capture));
        } else if (body instanceof Mono<? extends DataBuffer> monoBuffer) {
            return super.writeWith(monoBuffer.doOnNext(this::capture));
        }
        Flux<DataBuffer> buffer = Flux.from(body);
        return super.writeWith(buffer.doOnNext(this::capture));
    }

    private void capture(DataBuffer buffer) {
        try (DataBuffer.ByteBufferIterator byteBufferIterator = buffer.readableByteBuffers()) {
            while (byteBufferIterator.hasNext()) {
                int length = body.length();
                if (length < 1024) {
                    ByteBuffer bytes = byteBufferIterator.next();
                    this.body.append(StandardCharsets.UTF_8.decode(bytes.slice(0, Math.min(1024 - length, bytes.remaining()))));
                } else{ break;}
            }
        }
    }

    public String getFullBody() {
        return this.body.toString();
    }

}