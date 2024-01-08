package com.demo.atomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public class atomicIntegerTest {
    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger();
//        System.out.println(atomicInteger.get());
//        int num  = atomicInteger.get();
//        if(num < 3) {
//            System.out.println(num);
//        }
//        int i = atomicInteger.get();
//        System.out.println(i);
        AtomicInteger atomicInteger = new AtomicInteger();
        for(int i = 0; i<5; i++) {
            int num = atomicInteger.getAndIncrement();
            System.out.println(num);
        }
    }
}
