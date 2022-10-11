import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class debugStudyTest {
    public static void main(String[] args) {
//        testExceptionBreakpoint();
//        testThrowException();
//        testCondition();
//        testForceReturn();
//        testTraceCurrentStreamChain();
//        testEvaluateExpression();
    }

    /**
     * 测试异常断点
     */
    private static void testThrowExceptionBreakpoint() {
        try{
            // 测试异常断点的调试
            Object o = null;
            o.getClass();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 主动抛出异常
     * 常用场景：测试异常场景
     */
    private static void testThrowException() {
        return;
    }

    /**
     * 断点条件：在符合条件时才暂停
     */
    private static void testCondition() {
        int count = 0;
        for(int i = 0; i < 100; i++) {
            count = count + i;
        }
    }

    /**
     * 强制回退测试
     */
    private static void testForceReturn() {
        int i = 0;
        System.out.println("调用写数据库方法之前");
        writeDB(i);
    }

    private static void writeDB(int i) {
        System.out.println(i);
        System.out.println("开始写数据库......");
    }

    /**
     * Steam调试
     */
    private static void testTraceCurrentStreamChain() {
        List<Integer> numberList = new ArrayList<>();
        numberList.add(null);
        for(int i = 0; i < 20; i++) {
            numberList.add(i);
        }
        List<String> stringList = numberList.stream()
                .filter(Objects::nonNull)
                .filter(debugStudyTest::filterLteFive)
                .filter(i -> i % 2 == 0)
                .map(String::valueOf)
                .collect(Collectors.toList());
        System.out.println(stringList);
    }

    private static boolean filterLteFive(int i) {
        return i > 5;
    }

    /**
     * 执行表达式
     */
    private static void testEvaluateExpression() {
        int age = getAge();
        if(age > 18) {
            System.out.println("岁月是把杀猪刀");
        }else {
            System.out.println("他还只是一个孩子啊");
        }
    }

    private static int getAge() {
        return 17 + ThreadLocalRandom.current().nextInt(50);
    }
}
