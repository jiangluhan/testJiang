package com.spring.register.bean.condition;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("java7")
/*
 * 当环境变量包含java7的时候，Java7CalculateServiceImpl将会被注册到IOC容器中
 */
public class Java7CalculateServiceImpl implements CalculateService{
    @Override
    public Integer sum(Integer... value) {
        System.out.println("Java 7环境下执行");
        int result = 0;
        for (int i = 0; i <= value.length; i++) {
            result += i;
        }
        return result;
    }
}
