package com.wojiushiwo.server;

import com.wojiushiwo.server.dao.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.wojiushiwo.server.dao")
public class App {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


}
