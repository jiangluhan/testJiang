package com.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.quartz"})
public class QuartzTestApp {
    public static void main(String[] args) {
        SpringApplication.run(QuartzTestApp.class, args);
    }
}
