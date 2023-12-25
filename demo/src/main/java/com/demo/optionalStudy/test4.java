package com.demo.optionalStudy;

import java.util.Optional;

/**
 * 映射
 */
public class test4 {
    public static void main(String[] args) {

        /*
         * map(Function<T, U> mapper)：如果optional不为空，则将optional中的对象 t 映射成另外一个对象 u，并将 u 存放到一个新的optional容器中
         * flatMap(Function< T,Optional< U >> mapper)：跟上面一样，在optional不为空的情况下，将对象t映射成另外一个optional
         * 区别：map会自动将u放到optional中，而flatMap则需要手动给u创建一个optional
         */

        Student student = new Student("李四", 1);
//        Student student = null;
        Optional<Student> os = Optional.ofNullable(student);
        // 如果student对象不为空，就加一岁
//        Optional<Student> emp = os.map(e ->
//        {
//            e.setAge(e.getAge() + 1);
//            return e;
//        });

        System.out.println("------分隔符------");

        // map：判断emp的这个Optional是否为空，如果不为空，就打印信息；如果为空，就不作操作
//        emp.ifPresent(s -> System.out.println(s.getName() + "," + s.getAge()));

        // flatMap
        Optional<Integer> integer = os.flatMap(stu -> stringToInt(String.valueOf(stu.getAge()))).filter(i -> i < 2);
        // integer.isPresent() 有值返回true，不存在值返回false
        System.out.println(integer.isPresent());
        // 如果存在，返回值；不存在返回other
        System.out.println(integer.orElse(0));



    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}

