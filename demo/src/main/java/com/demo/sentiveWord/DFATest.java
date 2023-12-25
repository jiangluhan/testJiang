package com.demo.sentiveWord;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DFATest {
    public static void main(String[] args) {
        String content = "盼望着，盼望着，东风来了，春天的脚步近了。\n" +
                "一切都像刚睡醒的样子，欣欣然张开了眼。山朗润起来了，水涨起来了，太阳的脸红起来了。\n" +
                "小草偷偷地从土里钻出来，嫩嫩的，绿绿的。园子里，田野里，瞧去，一大片一大片满是的。坐着，躺着，打两个滚，踢几脚球，赛几趟跑，捉几回迷藏。风轻悄悄的，草软绵绵的。\n" +
                "桃树、杏树、梨树，你不让我，我不让你，都开满了花赶趟儿。红的像火，粉的像霞，白的像雪。花里带着甜味儿；闭了眼，树上仿佛已经满是桃儿、杏儿、梨儿。花下成千成百的蜜蜂嗡嗡地闹着，大小的蝴蝶飞来飞去。野花遍地是：杂样儿，有名字的，没名字的，散在草丛里，像眼睛，像星星，还眨呀眨的。\n" +
                "“吹面不寒杨柳风”，不错的，像母亲的手抚摸着你。风里带来些新翻的泥土的气息，混着青草味儿，还有各种花的香，都在微微润湿的空气里酝酿。鸟儿将窠巢安在繁花嫩叶当中，高兴起来了，呼朋引伴地卖弄清脆的喉咙，唱出宛转的曲子，与轻风流水应和着。牛背上牧童的短笛，这时候也成天在嘹亮地响。";
        // 定义敏感词库 后续可以对接数据库
        List<String> sensitiveWords = new ArrayList<>();

        sensitiveWords.add("水里");
        sensitiveWords.add("母亲的手");
        // 使用toArray()方法将List<String>转换为String[]
        String[] array = sensitiveWords.toArray(new String[0]);
        // 设置敏感词库
        Finder.addSensitiveWords(array);
        // 敏感词过滤替换字符为*
//        String replace = Finder.replace(content, '*');
        // 输出结果：我觉得**还行。
//        System.out.println(replace);
        long start = System.currentTimeMillis();
        // 找出文本中的敏感词
        Set<String> strings = Finder.find(content);
        System.out.println("查找敏感词耗时：" + ((System.currentTimeMillis()) - start));
        System.out.println(strings);
        String jsonStr = JSONUtil.toJsonStr(strings);
        System.out.println(jsonStr);
    }
}
