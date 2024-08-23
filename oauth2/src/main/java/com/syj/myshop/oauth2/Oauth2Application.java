package com.syj.myshop.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author SuYajiang
 * @email suyajiang@aoscript.com
 * @create 2024-08-22 10:47
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Oauth2Application {


    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class);

    }
}
