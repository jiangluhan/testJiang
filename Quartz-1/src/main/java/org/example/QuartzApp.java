package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: 测试启动类
 *
 */
@SpringBootApplication(scanBasePackages = {"org.example.*"})
public class QuartzApp {
    public static void main(String[] args) {
        SpringApplication.run(QuartzApp.class, args);
    }
}
