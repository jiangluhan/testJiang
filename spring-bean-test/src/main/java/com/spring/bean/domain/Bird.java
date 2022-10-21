package com.spring.bean.domain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Bird implements InitializingBean, DisposableBean {

    public Bird() {
        System.out.println("调用无参构造器创建Bird");
    }

    /*
        编写销毁方法
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("销毁bird");
    }

    /*
       编写初始化方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化bird");
    }
}
