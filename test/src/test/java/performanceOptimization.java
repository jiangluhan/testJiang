import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class performanceOptimization {

    public static void main(String[] args) {

    }


    // 并发/并行的实现方式通常有两种，如下:

    /**
     * 第一种 开线程直接怼，每循环一次都会新建一个线程来执行
     * @throws InterruptedException
     */
    public static void test1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // 烧水
                boilingWater();
                countDownLatch.countDown();
            }).start();
        }
        // 等待处理结束
        countDownLatch.await();
    }


    /**
     * 第二种 使用线程池
     */
    public static void test2() {
        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futureList.add(THREAD_POOL_EXECUTOR.submit(() -> {
                // 烧开水
                boilingWater();
            }));
        }
        for (Future future : futureList) {
            try {
                // 等待处理结束
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建线程池
     */
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            1,
            2,
            30,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(1000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    private static void boilingWater() {
        System.out.println("正在烧水...");
    }
}
