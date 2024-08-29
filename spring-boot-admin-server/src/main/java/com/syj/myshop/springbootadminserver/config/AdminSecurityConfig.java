package com.syj.myshop.springbootadminserver.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class AdminSecurityConfig {
    @Resource
    private AdminServerProperties adminServerProperties;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String adminContextPath = adminServerProperties.getContextPath();
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");
        // @formatter:off
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(adminContextPath + "/actuator/**").permitAll()
                        .requestMatchers(adminContextPath + "/assets/**").permitAll()
                        .requestMatchers(adminContextPath + "/login").permitAll()
                        .requestMatchers(adminContextPath + "/instances/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin->
                                formLogin.loginPage(adminContextPath + "/login").successHandler(successHandler)
                )
                .logout(logout->
                        logout.logoutUrl(adminContextPath + "/logout")
                                .logoutSuccessUrl(adminContextPath + "/login")
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(Customizer.withDefaults())
                ;


        return http.build();
        // @formatter:on
    }




}
