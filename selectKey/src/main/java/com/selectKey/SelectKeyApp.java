package com.selectKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: 测试启动类
 *
 */
@SpringBootApplication(scanBasePackages = {"com.selectKey.*"})
public class SelectKeyApp {
    public static void main(String[] args) {
        SpringApplication.run(SelectKeyApp.class, args);
    }
}
