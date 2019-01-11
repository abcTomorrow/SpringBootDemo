package com.wojiushiwo.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by 我就是我
 * 2018/11/12 上午10:05
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.wojiushiwo.product.client")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
