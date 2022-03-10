package com.selectKey;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Description: 测试启动类
 *
 */
@SpringBootApplication(scanBasePackages = {"com.selectKey.*"})
@MapperScan(basePackages = {"com.selectKey.dao"})
@EnableEurekaClient
public class SelectKeyApp {
    public static void main(String[] args) {
        SpringApplication.run(SelectKeyApp.class, args);
    }
}
