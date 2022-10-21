package com.spring.register.bean.config;

import com.spring.register.bean.FactoryBeanRegisterComponent.CherryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试使用FactoryBean注册组件
 */
@Configuration
public class WebConfig3 {

    @Bean
    public CherryFactoryBean cherryFactoryBean() {
        return new CherryFactoryBean();
    }
}
