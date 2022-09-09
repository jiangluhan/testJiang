import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        // list转换成数组
//        List<String> list = Arrays.asList("a","b","c");
//        String[] arrays = list.stream().toArray(String[]::new);
//        for (String array : arrays) {
//            System.out.println(array);
//        }


        // 构建一个 Person 集合
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));


    // 测试一下dev分支的代码到test

        // test 1
        // test 2

        // test 3
        // test 4

        List<Person> filtered = persons
                .stream() // 构建流
                .filter(p -> p.name.startsWith("P")) // 过滤出名字以 P 开头的
                .collect(Collectors.toList()); // 生成一个新的 List

        System.out.println(filtered);    // [Peter, Pamela]
    }
}
