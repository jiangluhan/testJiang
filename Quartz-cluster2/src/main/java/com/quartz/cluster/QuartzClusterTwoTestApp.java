package com.quartz.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.quartz.cluster"})
public class QuartzClusterTwoTestApp {
    public static void main(String[] args) {
        SpringApplication.run(QuartzClusterTwoTestApp.class, args);
    }
}

