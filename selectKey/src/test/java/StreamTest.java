import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
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

//        List<Person> filtered = persons
//                .stream() // 构建流
//                .filter(p -> p.name.startsWith("P")) // 过滤出名字以 P 开头的
//                .collect(Collectors.toList()); // 生成一个新的 List
//
//        System.out.println(filtered);    // [Peter, Pamela]


//        long beginTime = System.currentTimeMillis();
//
//        File file = new File("D:\\Users\\admin\\Desktop\\夹带测试(图片+文档+压缩包).zip");
//
//        String md5 = getMD5(file);
//
//        long endTime = System.currentTimeMillis();
//
//        System.out.println("MD5:" + md5 + "\n 耗时:" + ((endTime - beginTime) / 1000) + "s");

        String path = "D:\\Users\\admin\\Desktop\\favicon.ico";
        String md5 = getMD5(new File(path));
        System.out.println(md5);
    }

    public static String getMD5(File file) {
        FileInputStream fileInputStream = null;
        try {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(MD5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null){

                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
