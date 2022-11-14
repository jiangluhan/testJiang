package com.spring.async.controller;

import com.spring.async.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/testAsync")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestService testService;

//    @GetMapping("/sync")
//    public void testSync(){
//        long start = System.currentTimeMillis();
//        logger.info("同步方法开始");
//
//        testService.syncMethod();
//
//        logger.info("同步方法结束");
//        long end = System.currentTimeMillis();
//
//        logger.info("同步方法执行总耗时为：{} ms", end - start);
//    }
    /*
     * 同步方法开始
     * 同步方法结束
     * 同步方法执行总耗时为：2002 ms
     * 结论：可看到默认程序是同步的，由于sleep方法阻塞的原因，testSync方法执行了2秒钟以上
     */

//    @GetMapping("/async")
//    public void testAsync() {
//        long start = System.currentTimeMillis();
//        logger.info("异步方法开始");
//
//        testService.asyncMethod();
//
//        logger.info("异步方法结束");
//        long end = System.currentTimeMillis();
//
//        logger.info("异步方法执行总耗时为：{} ms", end - start);
//    }
    /*
     * 异步方法开始
     * 异步方法结束
     * 异步方法执行总耗时为：4 ms
     * 异步方法内部线程名称：task-1
     * 结论：可看到testAsync方法耗时极少，因为异步的原因，程序并没有被sleep方法阻塞，这就是异步调用的好处。同时异步方法内部会【新启一个线程】来执行，这里线程名称为task-1
     */
    /**
     * 当再次调用testAsync方法时，会发现控制台打印出如下信息：
     * 异步方法开始
     * 异步方法结束
     * 异步方法执行总耗时为：0 ms
     * 异步方法内部线程名称：task-2
     * 结论：异步方法内部线程是每次调用都会重新新建一个，并不会复用之前的，并且耗时为0ms
     * ==》默认情况下的异步线程池配置使得线程不能被重用，每次调用异步方法都会新建一个线程，我们可以自己定义异步线程池来优化
     */


//    @GetMapping("/customizeAsync")
//    public void customizeAsync() {
//        long start = System.currentTimeMillis();
//        logger.info("测试自定义异步线程池--》异步方法开始");
//
//        testService.customizeAsyncMethod();
//
//        logger.info("测试自定义异步线程池--》异步方法结束");
//        long end = System.currentTimeMillis();
//
//        logger.info("测试自定义异步线程池--》异步方法执行总耗时为：{} ms", end - start);
//    }
    /**
     * 测试自定义异步线程池--》异步方法开始
     * 测试自定义异步线程池--》异步方法结束
     * 测试自定义异步线程池--》异步方法执行总耗时为：4 ms
     * 测试自定义异步线程池--》异步方法内部线程名称：asyncThread1
     */

    @GetMapping("/asyncMethodFuture")
    public String asyncMethodFuture() throws Exception {
        long start = System.currentTimeMillis();
        logger.info("测试处理异步回调--》异步方法开始");

        Future<String> stringFuture = testService.asyncMethodFuture();
        // Future接口的get方法用于获取异步调用的返回值 【Future的get方法为阻塞方法，只有当异步方法返回内容了，程序才会继续往下执行】
//        String result = stringFuture.get();

        // get还有一个get(long timeout, TimeUnit unit)重载方法，我们可以通过这个重载方法设置超时时间，即异步方法在设定时间内没有返回值的话，直接抛出java.util.concurrent.TimeoutException异常
        String result = stringFuture.get(1, TimeUnit.SECONDS);

        logger.info("测试处理异步回调--》异步方法返回值：{}", result);

        logger.info("测试处理异步回调--》异步方法结束");

        long end = System.currentTimeMillis();
        logger.info("测试处理异步回调--》总耗时：{} ms", end - start);
        return stringFuture.get();
    }
    /**
     * 测试处理异步回调--》异步方法开始
     * 2022-11-14 14:10:04.479 [asyncThread2] INFO  com.spring.async.service.TestService - 测试处理异步回调--》异步方法内部线程名称：asyncThread2
     * 2022-11-14 14:10:04.479 [http-nio-8080-exec-3] INFO  com.spring.async.controller.TestController - 测试处理异步回调--》异步方法返回值：hello async
     * 2022-11-14 14:10:04.479 [http-nio-8080-exec-3] INFO  com.spring.async.controller.TestController - 测试处理异步回调--》异步方法结束
     * 2022-11-14 14:10:04.479 [http-nio-8080-exec-3] INFO  com.spring.async.controller.TestController - 测试处理异步回调--》总耗时：2002 ms
     * 通过返回结果我们可以看出Future的get方法为阻塞方法，只有当异步方法返回内容了，程序才会继续往下执行
     *
     */
}
