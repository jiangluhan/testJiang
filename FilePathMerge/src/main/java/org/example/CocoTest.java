package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CocoTest {
	
	public static void main(String[] args) {

        List<String> srcList = new ArrayList<>();
        srcList.add("D:\\318test");
        srcList.add("D:\\318test\\test1");
        srcList.add("D:\\319test");
//        srcList.add("D:\\");
        srcList.add("D:\\318test\\test1\\jiangluhan.zip");

        System.out.println(merge(srcList));
    }


    /**
     * windows下文件目录合并
     * @param srcList
     * @return
     */
    private static List<String> merge(List<String> srcList) {
        //结果集
        List<String> result = new ArrayList<>();
        //创建小顶堆
        Queue<String> queue = new PriorityQueue<>((x, y) -> (x.length() - y.length()));
        // 将排序后的数据放到小顶堆里面
        for (String s : srcList) {
            queue.offer(s);
        }
        // 遍历原始小顶堆
        while (!queue.isEmpty()) {
            // 创建新的小顶堆
            Queue<String> queueTemp = new PriorityQueue<>((x, y) -> (x.length() - y.length()));
            // 对原始小顶堆弹出数据，弹出以后，原始小顶堆会相应的减少数据
            String path = queue.poll();
            result.add(path);
            // 遍历原始小顶堆剩下的数据和弹出的数据一一进行比较
            for (String s : queue) {
                // 如果不是弹出数据的子集，就放到新的顶堆里面
                if (!s.startsWith(path )) {
                    queueTemp.offer(s);
                }
            }
            // 到这里相当于删掉了弹出数据的子集
            queue = queueTemp;
        }
        return result;
    }
}
