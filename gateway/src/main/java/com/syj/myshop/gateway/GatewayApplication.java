package com.syj.myshop.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        //Hooks.onOperatorDebug();
        ReactorDebugAgent.init();
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
        return http.csrf().disable().build();
    }
}
