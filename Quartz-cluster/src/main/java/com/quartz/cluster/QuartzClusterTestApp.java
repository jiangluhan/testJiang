package com.quartz.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.quartz.cluster"})
public class QuartzClusterTestApp {
    public static void main(String[] args) {
        SpringApplication.run(QuartzClusterTestApp.class, args);
    }
}

