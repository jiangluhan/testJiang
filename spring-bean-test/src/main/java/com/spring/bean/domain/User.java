package com.spring.bean.domain;

/**
 * 新增初始化方法和销毁方法
 */
public class User {

    public User() {
        System.out.println("调用无参构造函数创建User");
    }

    public void init(){
        System.out.println("初始化User");
    }

    public void destory(){
        System.out.println("销毁User");
    }
}
