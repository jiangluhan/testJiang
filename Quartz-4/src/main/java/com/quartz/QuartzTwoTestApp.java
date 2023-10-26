package com.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.quartz"})
public class QuartzTwoTestApp {
    public static void main(String[] args) {
        SpringApplication.run(QuartzTwoTestApp.class, args);
    }
}
