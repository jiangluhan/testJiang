package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<DetailServerDto> serverResult = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        strings.add("D:\\\\Edge WorkSpace");
        strings.add("D:\\\\318test\\\\000001.zip");
        DetailServerDto detailServerDto = new DetailServerDto(strings, "192.168.6.143:3306", "jiangluhan", "wj210523");
        serverResult.add(detailServerDto);

        serverResult.stream().filter(distinctByKey(e -> e.getServerAddress()))
                .collect(Collectors.toList())
                .forEach(adviceInfo1 -> System.out.println(adviceInfo1));
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}