package com.quartz.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.quartz.db"})
public class QuartzDBTestApp {
    public static void main(String[] args) {
        SpringApplication.run(QuartzDBTestApp.class, args);
    }
}

