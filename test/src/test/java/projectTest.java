import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class projectTest {
    private static final Integer ONE = 1;
    public static void main(String[] args) {
        testReadTxtFile();
    }



     // 测试读取txt文件，每行以换行符结尾
     public static void testReadTxtFile() {
         Map<String, Integer> map = new HashMap<String, Integer>();

         /* 读取数据 */
         try {
//             BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\Users\\admin\\Desktop\\test.txt")),
//                     "UTF-8"));
             /*
              * FileInputStream和FileReader都是用于读取文件的输入流，二者的不同在于，FileInputStream是以字节流的方式读入数据，而FileReader是以字符流的方式读取的。
              * saber万岁！！！ 这个字符内容包含了英文、中文、符号，所以采取字节流的形式读取的话，后面的万岁中文是读取不到的，但是采取字符流的话是可以的
              * 基于上面两点，这里采取字符流进行读取
              */
             FileReader fr = new FileReader("D:\\Users\\admin\\Desktop\\test.txt");
             BufferedReader br = new BufferedReader(fr);
             String lineTxt = null;
             List<String> result = new ArrayList();
             while ((lineTxt = br.readLine()) != null) {
                 result.add(lineTxt);
             }
             System.out.println(result);
             br.close();
             fr.close();
         } catch (Exception e) {
             System.err.println("read errors :" + e);
         }

//         /* 输出数据 */
//         try {
//             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("D:\\Users\\admin\\Desktop\\test-1.txt")),
//                     "UTF-8"));
//
//             for (String name : map.keySet()) {
//                 bw.write(name + " " + map.get(name));
//                 bw.newLine();
//             }
//             bw.close();
//         } catch (Exception e) {
//             System.err.println("write errors :" + e);
//         }
//         System.out.printf(map.toString());
     }
}


