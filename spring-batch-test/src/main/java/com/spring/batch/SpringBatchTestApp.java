package com.spring.batch;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing // 开启Spring Batch批处理功能
public class SpringBatchTestApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBatchTestApp.class, args);
    }
}
