import java.util.concurrent.CountDownLatch;

public class countDownLatchTest {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        System.out.println("全班同学开始考试，一共两个学生");
        new Thread(() -> {
            System.out.println("第一个学生交卷，countDownLatch减1");
            countDownLatch.countDown();
        }).start();
        // 如果注释掉下面这个线程的运行，那么这个方法会一直等待，因为countDownLatch并未执行完，test方法不能结束
        new Thread(() -> {
            System.out.println("第二个学生交卷，countDownLatch减1");
            countDownLatch.countDown();
        }).start();
        // 也就是说，如果我额外新增两个线程的话，可能我新增的线程中的某一个会比countDownLatch定义的线程先执行 针对于下面两个线程都执行的时候
        new Thread(() -> {
            System.out.println("第三个学生交卷，非countDownLatch设置的线程");
            countDownLatch.countDown();
        }).start();
//        new Thread(() -> {
//            System.out.println("第四个学生交卷，非countDownLatch设置的线程");
//            countDownLatch.countDown();
//        }).start();
        /*
         * 如果只额外添加一个线程的话，那么不论执行多少次，这个线程都会在countDownLatch后面执行
         */
        try{
            countDownLatch.await();
            // 测试异常断点的调试
            Object o = null;
            o.getClass();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("老师清点试卷，在此之前，只要一个学生没交，countDownLatch不为0，不能离开考场");
        /*
         * 该结果是超出countDownLatch设定的线程数所执行的结果，原本猜测可能是不会执行或者报错，实际是在countDownLatch执行结束以后在执行该线程，test方法才算彻底结束
         * 全班同学开始考试，一共两个学生
         * 第一个学生交卷，countDownLatch减1
         * 第二个学生交卷，countDownLatch减1
         * 老师清点试卷，在此之前，只要一个学生没交，countDownLatch不为0，不能离开考场
         * 第三个学生交卷，countDownLatch减1
         */
    }
}
