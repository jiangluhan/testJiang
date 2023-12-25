package com.demo.optionalStudy;

import java.util.Optional;

/**
 * 过滤
 */
public class test3 {
    public static void main(String[] args) {
        Student student = new Student("李四", 3);
        Optional<Student> os = Optional.ofNullable(student);
        // 如果optional不为空，则执行断言函数p，如果p的结果为true，则返回原本的optional，否则返回空的optional
        os.filter(p -> p.getName().equals("张三")).ifPresent(x -> System.out.println("OK"));
    }
}
