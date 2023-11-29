package com.google.authenticator.config;

import com.google.authenticator.service.GoogleAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("liquibase")
@Slf4j
public class InitConfig implements InitializingBean {

    @Autowired
    private GoogleAuthService googleAuthService;

    @Override
    public void afterPropertiesSet() {
        // 初始化用户
        googleAuthService.initUser();
    }
}
