package com.google.authenticator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.google.authenticator"})
@MapperScan({"com.google.**.dao"})
public class GoogleAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleAuthServiceApplication.class, args);
	}
}
