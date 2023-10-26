package com.quartz.memory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.quartz.memory"})
public class QuartzMemoryTestApp {
    public static void main(String[] args) {
        SpringApplication.run(QuartzMemoryTestApp.class, args);
    }
}
