package com.spring.register.bean.config;

import com.spring.register.bean.imp.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 测试使用导入组件
 */
@Configuration
//@Import({Hello.class}) // 测试快速导入一个组件
//@Import({MyImportSelector.class}) // 测试一次性导入多个组件
@Import({MyImportBeanDefinitionRegistrar.class}) //测试导入组件：ImportBeanDefinitionRegistrar
public class WebConfig2 {

}
