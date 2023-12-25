package com.demo.optionalStudy;

import java.util.Optional;

/**
 * 获取Optional容器的对象
 */
public class test2 {

    public static void main(String[] args) {
        Student student = null;
        Optional<Student> os = Optional.ofNullable(student);
        // 如果student为空，那么创建出来的os在.get()方法的时候，是会报错的
        // Exception in thread "main" java.util.NoSuchElementException: No value present
//        Student stu = os.get();

        // 可以使用.orElse(xxx)，作为如果为空的处理，xxx ---> 当new出来的Optional为空的时候，程序需要做的处理
        // 如果存在，返回值；不存在返回other
        Student stu2 = os.orElse(new Student("张三", 18));
        System.out.println("当student为空的时候，处理后的stu2信息为：{}" + stu2.getName() + "," + stu2.getAge());

        System.out.println("------分隔符------");

        // 还有另一种方式处理
        // orElseGet()就是当student为空的时候，返回通过Supplier供应商函数创建的对象
        Student stu3 = os.orElseGet(() -> new Student("李四", 18));
        System.out.println("当student为空的时候，处理后的stu3信息为：{}" + stu3.getName() + "," + stu3.getAge());

        System.out.println("------分隔符------");

        try {
            // orElseThrow()就是当student为空的时候，可以抛出我们指定的异常
//            os.orElseThrow(() -> new Exception());
            os.orElseThrow(Exception::new); // 上面是另外一种写法
        }catch (Exception e) {
            System.out.println("当student为空的时候，我们可以选择抛出异常，抛出异常！");
        }
    }
}
