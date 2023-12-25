package com.demo.fileJson;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.FileOutputStream;
import java.io.IOException;

public class fileJsonTest {
    public static void main(String[] args) throws IOException {
        test();
    }

    /**
     * 将json写入文件
     * @throws IOException
     */
    public static void test() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("age", 30);
        jsonObject.put("city", "合肥");

        String jsonStr = JSONUtil.toJsonStr(jsonObject);

        try(FileOutputStream outputStream = new FileOutputStream("D:\\Users\\admin\\Desktop\\out.json")) {
            byte[] bytes = jsonStr.getBytes();
            outputStream.write(bytes);
        }
        System.out.println("文件生成成功！");
    }
}
