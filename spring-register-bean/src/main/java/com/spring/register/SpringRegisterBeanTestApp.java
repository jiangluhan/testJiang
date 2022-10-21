package com.spring.register;

import com.spring.register.bean.config.WebConfig3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringRegisterBeanTestApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringRegisterBeanTestApp.class, args);

        /*
         * TEST-1
         */
        // 返回 IOC 容器，使用注解配置，传入配置类 【此时并没有指定bean的自定义名称，用的是默认bean名称】
        // 结果：User(name=my spring register bean, age=18)
//        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
//        User user = context.getBean(User.class);
//        System.out.println(user);


        /*
         * TEST-2
         */
        // 当指定bean的名称的时候 同时也测试了同一个返回类型但是不同bean的名称获取 【user、user1】
//        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
//        String[] beanNames = context.getBeanNamesForType(User.class);
//        Arrays.stream(beanNames).forEach(System.out::println);
        /*
         * 结论：context.getBeanNamesForType(xxx.class);
         * 该方法用于获取 Spring 容器中指定类型的所有 JavaBean 的名称
         * 也就是说，所有Bean的返回值类型都为xxx类型的bean集合
         */


        /*
         * TEST-3
         */
//        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
//        // 查看基于注解的 IOC容器中所有组件名称
//        String[] beanNames = context.getBeanDefinitionNames();
//        Arrays.stream(beanNames).forEach(System.out::println);
        /*
         * 结果：
         * webConfig
         * userController
         * userMapper
         * user
         * userService
         */

        /*
         * TEST-4
         */
        // 测试组件作用域@Scope，采用WebConfig1
//        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig1.class);
//        Object user1 = context.getBean("user");
//        Object user2 = context.getBean("user");
//        System.out.println(user1 == user2);// true


        /*
         * TEST-5
         */
        // 测试懒加载@Lazy：针对单例模式而言的，IOC容器中的组件默认是单例的，容器启动的时候会调用方法创建对象然后纳入到IOC容器中
//        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig1.class);
//        System.out.println("容器创建完毕");
        /*
         * 结果：在IOC容器创建完毕之前，组件已经添加到容器中了【非添加懒加载注解的结果】
         * 往IOC容器中注册user bean
         * 容器创建完毕
         */
        /*
         * 结论：在单例模式中，IOC容器创建的时候不会马上去调用方法创建对象并注册，只有当组件第一次被使用的时候才会调用方法创建对象并加入到容器中
         * 添加懒加载注解的结果：
         * 容器创建完毕
         */


        /*
         * TEST-6
         */
//        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig1.class);
//        System.out.println("容器创建完毕");
//        Object user = context.getBean("user");
//        Object user1 = context.getBean("user");
        /*
         * 测试当在懒加载的情况下，是否是在第一次被调用以后组件才被注册到IOC容器中
         * 结果：
         * 容器创建完毕
         * 往IOC容器中注册user bean
         * 结论：确实是在第一次被调用以后才会被注册到IOC容器中，然后后续的调用就会直接使用IOC容器里的了，就不会在重新注册了
         */

        /*
         * TEST-7：测试条件组件注册的第二个注解【@Condition】
         */
//        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig1.class);
//        String[] beanNames = context.getBeanDefinitionNames();
//        Arrays.stream(beanNames).forEach(System.out::println);
        /*
         * 结果：user确实是被注册进来了
         * webConfig1
         * user
         * 结论：这个MyCondition设置的是基于Windows下的组件才能注册进来，如果是基于别的操作系统的话，就不会注册进来
         */

        /*
         * TEST-8：测试条件组件注册的第二个注解【@Profile】
         * 注意：在测试这个的时候，需要把SpringApplication.run(SpringRegisterBeanTestApp.class, args);注释掉
         */
//        ConfigurableApplicationContext context1 = new SpringApplicationBuilder(SpringRegisterBeanTestApp.class)
//                .web(WebApplicationType.NONE)
////                .profiles("java8")
//                .profiles("java7")
//                .run(args);
//
//        CalculateService service = context1.getBean(CalculateService.class);
//        System.out.println("求合结果： " + service.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        /*
         * 结果：
         * Java 8环境下执行
         * 求合结果： 55
         */
        /*
         * 将Java8改为Java7时
         * 结果：
         * Java 7环境下执行
         * 求合结果： 55
         */

        /*
         * TEST-9 导入组件 【使用WebConfig2进行测试】
         */
//        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig2.class);
//        String[] beanNames = context.getBeanDefinitionNames();
//        Arrays.stream(beanNames).forEach(System.out::println);
        /*
         * 快速导入一个组件
         * 结果：
         * webConfig2
         * com.spring.register.bean.domain.Hello
         */
        /*
         * 一次性导入多个组件
         * 结果：
         * webConfig2
         * com.spring.register.bean.domain.Apple
         * com.spring.register.bean.domain.Banana
         * com.spring.register.bean.domain.Watermelon
         */
        /*
         * 测试导入组件：ImportBeanDefinitionRegistrar
         * 结果：
         * webConfig2
         * Strawberry
         */

        /*
         * TEST-10 使用FactoryBean注册组件 【使用WebConfig3进行测试】
         */
//        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig3.class);
//        Object cherry = context.getBean("cherryFactoryBean");
//        System.out.println(cherry.getClass());
        /*
         * 结果：
         * class com.spring.register.bean.domain.Cherry
         * 结论：可看到，虽然我们获取的是Id为cherryFactoryBean的组件，但其获取到的实际是getObject方法里返回的对象。
         *      如果我们要获取cherryFactoryBean本身，则可以像下面这样测试
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig3.class);
        Object cherryFactoryBean = context.getBean("&cherryFactoryBean");
        System.out.println(cherryFactoryBean.getClass());
        /*
         * 结果：
         * class com.spring.register.bean.FactoryBeanRegisterComponent.CherryFactoryBean
         * 结论：为什么加上&前缀就可以获取到相应的工厂类了呢，查看BeanFactory的源码会发现原因:
         * public interface BeanFactory {
         *      String FACTORY_BEAN_PREFIX = "&";
         * }
         */
    }
}
