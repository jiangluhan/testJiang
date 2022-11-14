package com.spring.async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class TestService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    // 开启异步支持后，只需要在方法上加上@Async注解便是异步方法了
    public void asyncMethod() { // 异步方法
        sleep();
        logger.info("异步方法内部线程名称：{}", Thread.currentThread().getName());
    }

    public void syncMethod() { // 同步方法
        sleep();
    }

    /**
     * 测试自定义异步线程池
     * 要使用该线程池，只需要在@Async注解上指定线程池Bean名称即可
     */
    @Async("asyncThreadPoolTaskExecutor")
    public void customizeAsyncMethod() {
        sleep();
        logger.info("测试自定义异步线程池--》异步方法内部线程名称：{}", Thread.currentThread().getName());
    }

    /**
     * 处理异步回调：如果异步方法具有返回值的话，需要使用Future来接收回调值
     */
    @Async("asyncThreadPoolTaskExecutor")
    public Future<String> asyncMethodFuture() {
        sleep();
        logger.info("测试处理异步回调--》异步方法内部线程名称：{}", Thread.currentThread().getName());
        return new AsyncResult<>("hello async");
    }

    // sleep()方法用于让当前线程阻塞2秒钟
    private void sleep() {
        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
