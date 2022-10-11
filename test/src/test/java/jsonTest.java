import com.alibaba.fastjson.JSONObject;

public class jsonTest {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        // 获取打印机名称，读取attr json字符串
        String attr = "{\"printerName\":\"HP4\"}";
        JSONObject jsonObject = JSONObject.parseObject(attr);
        String printerName = (String) jsonObject.get("printerName");
        System.out.println(printerName);
    }
}
