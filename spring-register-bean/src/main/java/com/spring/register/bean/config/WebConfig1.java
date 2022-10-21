package com.spring.register.bean.config;

import com.spring.register.bean.condition.MyCondition;
import com.spring.register.bean.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 测试使用条件注册组件
 */
@Configuration
public class WebConfig1 {

//     @Bean
//     @Lazy
//     public User user() {
//          System.out.println("往IOC容器中注册user bean");
//          return new User("test 懒加载@Lazy", 20);
//     }

     @Bean
     @Conditional(MyCondition.class)
     public User user() {
          return new User("test condition", 20);
     }
}
