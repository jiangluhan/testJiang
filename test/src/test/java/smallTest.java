import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于测试一些比较小的且简单的测试验证，多是公司业务代码需要进行一些小验证的时候可以在这个类下面验证
 */
public class smallTest {

    public static void main(String[] args) {
//        // 测试判断map是否为空
//        Map<String, String> map = new HashMap<>();
//        map.put("1", "test");
//        if(map.entrySet().isEmpty()) {
//            System.out.println("map为空");
//        }else {
//            System.out.println("map不为空");
//        }
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime dateTime = LocalDateTime.parse("2022-11-03 11:41", df);
//        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        System.out.println(df2.format(dateTime));
        String str = "2022-11-03 11:41";
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = formatter.parse(str);
            System.out.println(date);
            System.out.print("格式正确！");
        } catch (Exception e) {
            System.out.println("格式错误！");
        }
    }
}
