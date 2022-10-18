public class jsonTest {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        // 获取打印机名称，读取attr json字符串
//        String attr = "{\"20210002,20210003\"}";
//        JSONObject jsonObject = JSONObject.parseObject(attr);
//        String printerName = (String) jsonObject.get("printerName");
//        String[] split = attr.split(",");
//        System.out.println(split[0]);
//        System.out.println(printerName);

        String text = "This,is,a,comma,seperated,string.";
        String[] array = text.split(",");
//        for(String value:array) {
//            System.out.print(value + " ");
//        }
        System.out.println(array[0]);
        System.out.println(array[0].substring(0, 1));
    }
}
