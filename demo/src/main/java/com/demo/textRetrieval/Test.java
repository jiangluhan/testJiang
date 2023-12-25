package com.demo.textRetrieval;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String text = "这是一段包含敏感词的文本，比如敏感词1和敏感词2。ssssssssssssssssssssssadasfafasfsdf gs gsg";
        String[] sensitiveWords = {"敏感词1", "敏感词2", "哈哈哈哈"};

        for (String word : sensitiveWords) {
            Pattern pattern = Pattern.compile(word);
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                System.out.println("敏感词：" + matcher.group());
            }
        }
    }
}
