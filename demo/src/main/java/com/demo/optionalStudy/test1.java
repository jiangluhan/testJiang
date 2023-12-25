package com.demo.optionalStudy;

import java.util.Optional;

/**
 * 判断Optional容器中是否包含对象；如果包含对象，可以获取对象的相关属性
 */
public class test1 {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("zhangsan");
        student.setAge(10);
        Optional<Student> os = Optional.ofNullable(student);
        // isPresent()，判断是否有值，有值返回true，无值返回false
        boolean present = os.isPresent();
        System.out.println("判断os是否包含对象：{}" + present);

        System.out.println("------分隔符------");
        // isPresent和ifPresent一个是 is；一个是 if

        // 利用Optional的ifPresent方法做出如下：当student不为空的时候将name赋值为张三
        // ifPresent可以选择带一个消费函数的实例，可以对已存在的对象进行处理【可以先将上述赋值代码注释掉在测试】
//        os.ifPresent(p -> p.setName("张三"));
//        System.out.println("重置student以后的信息为：{}" + student.getName() + "," + student.getAge());

//        System.out.println("------分隔符------");

        // 如果student不为空，我怎么获取对象里面的值？可以用两种方式获取
        /*
         * 1、先判断这个Optional是否为空，如果不为空，可以根据lambda表达式获取相关属性
         * 2、通过.get()获取对象，在通过对象获取相关属性
         */
        os.ifPresent(p -> System.out.println("通过ifPresent()判断是否为空，在获取后的信息为：{}" + p.getName()));
        System.out.println("通过.get()获取的信息为：{}" + os.get().getName());
    }
}
