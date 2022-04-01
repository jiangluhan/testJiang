package com.swaggerToWord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
    @author: jiangluhan
 */
@SpringBootApplication
@EnableSwagger2
public class SwaggerToWordApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerToWordApplication.class, args);
    }
}
