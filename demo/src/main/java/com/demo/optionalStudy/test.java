package com.demo.optionalStudy;

import java.util.Optional;

/**
 * 创建Optional类对象的方法
 */
public class test {

    public static void main(String[] args) {
        // 声明一个空的Optional
        Optional<Object> empty = Optional.empty();
        System.out.println("这是一个空的Optional: {}" + empty);

        System.out.println("------分隔符------");

        // 依据一个非空值创建Optional Optional.of(xxx) ---> xxx不能为空
        Student student = new Student();
        Optional<Object> of = Optional.of(student);
        System.out.println("这是一个非空的Optional，内容为：{}" + of);

        System.out.println("------分隔符------");

        // 可接受null的Optional Optional.ofNullable(xxx) ---> xxx可为空
        Student studentNull = null;
        Optional<Student> os = Optional.ofNullable(studentNull);
        System.out.println("这是一个可为空的Optional，内容为：{}" + os);
    }
}
