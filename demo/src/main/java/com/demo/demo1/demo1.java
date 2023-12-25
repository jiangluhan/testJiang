package com.demo.demo1;


import com.demo.optionalStudy.Student;

/**
 * 值传递 引用传递
 */
public class demo1 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Student s1 = new Student("小张");
        Student s2 = new Student("小李");
        demo1.swap(s1, s2);
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
    }

    public static void swap(Student s1, Student s2) {
        Student temp = s1;
        s1 = s2;
        s2 = temp;
        System.out.println("new s1:" + s1.getName());
        System.out.println("new s2:" + s2.getName());
        s1.setName("jiangluhan");
    }
}


