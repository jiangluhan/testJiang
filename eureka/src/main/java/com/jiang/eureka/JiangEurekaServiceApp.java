package com.jiang.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author wang
 * @des eureka service启动类
 * @date 2021/8/11 11:17
 */
@SpringBootApplication
@EnableEurekaServer
public class JiangEurekaServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(JiangEurekaServiceApp.class, args);
    }

    /**
     * 防止eureka client 通过用户名和密码注册不进去
     */
    @EnableWebSecurity
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable().authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic();
        }
    }

}
