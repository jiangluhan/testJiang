package com.spring.register.bean.condition;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Profile("java8")
/*
 * 当环境变量包含java8的时候，Java8CalculateServiceImpl将会被注册到IOC容器中
 */
public class Java8CalculateServiceImpl implements CalculateService{
    @Override
    public Integer sum(Integer... value) {
        System.out.println("Java 8环境下执行");
        return Arrays.stream(value).reduce(0, Integer::sum);
    }
}
