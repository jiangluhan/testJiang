package com.tool;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 时间相关工具类
 */
public class TimeUtil {
    public static void main(String[] args) throws InterruptedException {
        computeBusinessTime();
        System.out.println("##########分隔符##########");
        coverTimeFormat();
    }


    /**
     * 获取时间秒数  比如：计算耗时、计算调用接口花费时长
     * @return double
     * @throws InterruptedException
     */
    private static double computeBusinessTime() throws InterruptedException {
        Instant start = Instant.now();
        // 在这里处理业务即可
        System.out.println("----处理业务----");
        Thread.sleep(3000);
        int nano = Duration.between(start, Instant.now()).getNano();
        double cost = nano / 1_000_000_000.0; // 将Nano除以10亿得到秒数
        System.out.println("处理业务耗时：" + cost);
        return cost;
    }

    /**
     * 转换时间对象
     * @return String
     */
    private static String coverTimeFormat() {
        Instant start = Instant.now();
        // 转换为LocalDateTime对象
        LocalDateTime localDateTime = LocalDateTime.ofInstant(start, ZoneId.systemDefault());
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // 格式化为字符串
        String formattedDateTime = localDateTime.format(formatter);
        System.out.println("转换后的时间格式是：" + formattedDateTime);
        return formattedDateTime;
    }
}
