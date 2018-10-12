package com.yidao.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.yidao.core","com.yidao.goods"})
public class AppGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppGoodsApplication.class, args);
    }
}
