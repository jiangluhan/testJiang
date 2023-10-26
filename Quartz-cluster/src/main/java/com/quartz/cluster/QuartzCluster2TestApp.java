package com.quartz.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.quartz.cluster"})
public class QuartzCluster2TestApp {
    public static void main(String[] args) {
        SpringApplication.run(QuartzCluster2TestApp.class, args);
    }
}

