import java.util.HashMap;
import java.util.Map;

/**
 * 用于测试一些比较小的且简单的测试验证，多是公司业务代码需要进行一些小验证的时候可以在这个类下面验证
 */
public class smallTest {

    public static void main(String[] args) {
        // 测试判断map是否为空
        Map<String, String> map = new HashMap<>();
        map.put("1", "test");
        if(map.entrySet().isEmpty()) {
            System.out.println("map为空");
        }else {
            System.out.println("map不为空");
        }
    }
}
