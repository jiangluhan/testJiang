package com.spring.register.bean.config;

import com.spring.register.bean.filter.MyTypeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(value = "com.spring.register.bean",
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION,
//                        classes = {Controller.class, Repository.class}),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = User.class)
//        })
// excludeFilters：排除一些组件的扫描
// 根据注解来排除（type = FilterType.ANNOTATION）,这些注解的类型为classes = {Controller.class, Repository.class}。即Controller和Repository注解标注的类不再被纳入到IOC容器中
// 根据指定类型类排除（type = FilterType.ASSIGNABLE_TYPE），排除类型为User.class，其子类，实现类都会被排除
//        includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class)
//        }, useDefaultFilters = false)
// includeFilters：哪些组件需要被扫描
// 上述配置了只将Service纳入IOC容器
// 并且需要用useDefaultFilters = false来关闭Spring默认的扫描策略才能让我们的配置生效（Spring Boot入口类的@SpringBootApplication注解包含了一些默认的扫描策略）
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)
        })
// 自定义过滤组件：MyTypeFilter过滤的是当被扫描的类名包含er时候，即匹配成功
// 然后配合excludeFilters使用的时候，就是排除掉被扫描的类名包含er的类
public class WebConfig {

    // Bean名称默认为方法名 当然我们也可以通过@Bean("myUser")方式来将组件名称指定为myUser
//    @Bean("myUser")
//    @Bean
//    public User user() {
//        return new User("my spring register bean", 18);
//    }
//
//    // 用于测试context.getBeanNamesForType
//    @Bean("myUser1")
//    public User user1() {
//        return new User("my user1", 19);
//    }
}
