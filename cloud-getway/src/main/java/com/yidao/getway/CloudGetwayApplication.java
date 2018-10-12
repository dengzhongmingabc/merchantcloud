package com.yidao.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class CloudGetwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGetwayApplication.class, args);
    }
}
