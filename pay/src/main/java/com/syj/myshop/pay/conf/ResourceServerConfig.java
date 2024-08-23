package com.syj.myshop.pay.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author SuYajiang
 * @email suyajiang@aoscript.com
 * @create 2024-08-22 10:00
 */
@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http
        //        .authorizeHttpRequests((requests) -> requests
        //                .requestMatchers("/api/public").permitAll()
        //                .requestMatchers("/api/private").authenticated()
        //        )
        //        .oauth2ResourceServer((oauth2) -> oauth2
        //                .jwt((jwt) -> Customizer.withDefaults())
        //        )
        //
        //        .oauth2Client(Customizer.withDefaults())
        //        .oauth2Login(Customizer.withDefaults())
        //        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        //        .csrf((csrf) -> csrf.disable());
        //
        //return http.build();


        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/public").permitAll()
                        .requestMatchers("/api/private").authenticated()
                )
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt((jwt) -> Customizer.withDefaults())
                )
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf((csrf) -> csrf.disable());

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Ensure this URI matches the issuer in your authorization server's configuration
        return JwtDecoders.fromIssuerLocation("http://127.0.0.1:9000");
    }

    //@Bean
    //public JwtAuthenticationConverter jwtAuthenticationConverter() {
    //    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    //    converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
    //    return converter;
    //}
}
