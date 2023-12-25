package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: 测试启动类
 *
 */
@SpringBootApplication(scanBasePackages = {"com.demo.*"})
public class HttpClientTestApp {
    public static void main(String[] args) {
        SpringApplication.run(HttpClientTestApp.class, args);
    }
}
