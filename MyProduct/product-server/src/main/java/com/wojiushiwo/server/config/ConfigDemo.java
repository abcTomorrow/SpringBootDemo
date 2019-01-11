package com.wojiushiwo.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 我就是我
 * 2018/12/18 上午10:39
 */
@RestController
@ConfigurationProperties(prefix = "")
public class ConfigDemo {
    @Value("${server.port}")
    private int port;

    @GetMapping("/port")
    public Integer getPort() {
        return port;
    }
}
