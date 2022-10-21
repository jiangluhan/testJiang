package com.spring.bean.config;

import com.spring.bean.domain.Bird;
import com.spring.bean.domain.Fish;
import com.spring.bean.domain.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册相关的bean
 */
@Configuration
public class webConfig {

    /**
     * 指定特定的初始化方法和销毁方法，和User里面的初始化和销毁方法对应
     * @return
     */
//    @Bean(initMethod = "init", destroyMethod = "destory")
//    @Scope("prototype")
//    public User user() {
//        return new User();
//    }

    @Bean
    public Fish fish() {
        return new Fish();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }

    @Bean
    public Bird bird() {
        return new Bird();
    }
}
