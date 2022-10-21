package com.spring.bean;

import com.spring.bean.config.webConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(scanBasePackages = "com.spring.bean.*")
public class SpringBeanTestApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBeanTestApp.class, args);

        /*
         * TEST-1
         * 单例模式，【使用注解@Scope("prototype")】：先调用对象的无参构造器创建对象，然后调用初始化方法，在容器关闭的时候调用销毁方法
         * 多例模式下【使用注解@Scope("prototype")】：IOC容器启动的时候并不会去创建对象，而是在每次获取的时候才会去调用方法创建对象，创建完对象后再调用初始化方法。
         *                                        但在容器关闭后，Spring并没有调用相应的销毁方法，这是因为在多例模式下，容器不会管理这个组件（只负责在你需要的时候创建这个组件），
         *                                        所以容器在关闭的时候并不会调用相应的销毁方法。
         */
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(webConfig.class);// 返回 IOC 容器，使用注解配置，传入配置类
//        System.out.println("容器创建完毕");
//        User user = context.getBean(User.class);
//        // 关闭 IOC 容器
//        context.close();

        /*
         * TEST-2
         * 使用的是Bird类测试
         */
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(webConfig.class);
//        System.out.println("容器创建完毕");
//        context.close();

        /*
         * TEST-3
         * 使用的是Fish类测试 这两个注解并非Spring提供，而是JSR250规范提供。
         */
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(webConfig.class);
//        System.out.println("容器创建完毕");
//        context.close();

        /*
         * TEST-4
         * 使用的是Fish、MyBeanPostProcessor类测试
         */
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(webConfig.class);
//        System.out.println("容器创建完毕");
//        context.close();
        /*
         * 当只有一个bean的时候
         * 结果：
         * 调用无参构造函数创建Fish
         * fish 初始化之前调用
         * 初始化Fish
         * fish 初始化之后调用
         * 容器创建完毕
         * 销毁Fish
         */

        /*
         * TEST-5
         * 使用的是Bird、Fish、MyBeanPostProcessor类测试
         */
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(webConfig.class);
//        System.out.println("容器创建完毕");
//        context.close();
        /*
         * 当有多个bean的时候
         * 结果：
         * 调用无参构造器创建Bird
         * bird 初始化之前调用
         * 初始化bird
         * bird 初始化之后调用
         * 调用无参构造函数创建Fish
         * fish 初始化之前调用
         * 初始化Fish
         * fish 初始化之后调用
         * 容器创建完毕
         * 销毁Fish
         * 销毁bird
         * 结论：在销毁的时候，好像并不是创一个销毁一个，而是按照出栈的方式，先进后销毁【先创建后销毁】 那这个创建的顺序是和webConfig里面的上下顺序有关系吗？下述测试
         */

        /*
         * TEST-6
         * 使用的是Fish、Bird、MyBeanPostProcessor类测试 和TEST-5测试进行比对
         */
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(webConfig.class);
//        System.out.println("容器创建完毕");
//        context.close();
        /*
         * 当调用上述bean的顺序的时候
         * 结果：
         * 调用无参构造函数创建Fish
         * fish 初始化之前调用
         * 初始化Fish
         * fish 初始化之后调用
         * 调用无参构造器创建Bird
         * bird 初始化之前调用
         * 初始化bird
         * bird 初始化之后调用
         * 容器创建完毕
         * 销毁bird
         * 销毁Fish
         * 结论：实验证明，销毁的顺序和bean的创建顺序有关系，而bean的创建顺序和webConfig文件里面的上下文顺序有关系
         * 猜想：这次实验，我只更换了Bird和Fish的顺序，那如果我把MyBeanPostProcessor类放在Bird和Fish两者之间，会怎么样呢？下述实验测试
         */

        /*
         * TEST-7
         * 使用的是Fish、MyBeanPostProcessor、Bird类测试
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(webConfig.class);
        System.out.println("容器创建完毕");
        context.close();
        /*
         * 实验证明：MyBeanPostProcessor的顺序与位置不影响bean的创建和销毁
         * 结果就不粘贴了，和TEST-6结果一致
         */
    }
}
