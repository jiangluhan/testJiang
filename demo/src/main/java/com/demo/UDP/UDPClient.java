package com.demo.UDP;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UDPClient {
    public static void main(String[] args) {
        try {
            // 创建UDP套接字 如果不写端口号的话默认会自动分配端口号
            DatagramSocket clientSocket = new DatagramSocket();

            // 创建要发送的数据
//            String message = "Hello, UDP!";
//            byte[] sendData = message.getBytes();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "test");
            jsonObject.put("age", 30);
            jsonObject.put("city", "合肥");
            jsonObject.put("height", "盼望着，盼望着，东风来了，春天的脚步近了。一切都像刚睡醒的样子，欣欣然张开了眼。山朗润起来了，水涨起来了，太阳的脸红起来了。小草偷偷地从土里钻出来，嫩嫩的，绿绿的。园子里，田野里，瞧去，一大片一大片满是的。坐着，躺着，打两个滚，踢几脚球，赛几趟跑，捉几回迷藏。风轻悄悄的，草软绵绵的。桃树、杏树、梨树，你不让我，我不让你，都开满了花赶趟儿。红的像火，粉的像霞，白的像雪。花里带着甜味儿；闭了眼，树上仿佛已经满是桃儿、杏儿、梨儿。花下成千成百的蜜蜂嗡嗡地闹着，大小的蝴蝶飞来飞去。野花遍地是：杂样儿，有名字的，没名字的，散在草丛里，像眼睛，像星星，还眨呀眨的。吹面不寒杨柳风”，不错的，像母亲的手抚摸着你。风里带来些新翻的泥土的气息，混着青草味儿，还有各种花的香，都在微微润湿的空气里酝酿。鸟儿将窠巢安在繁花嫩叶当中，高兴起来了，呼朋引伴地卖弄清脆的喉咙，唱出宛转的曲子，与轻风流水应和着。牛背上牧童的短笛，这时候也成天在嘹亮地响。");

            String jsonStr = JSONUtil.toJsonStr(jsonObject);

            // 需要生成的文件路径，后期要删除
            String path = "D:\\Users\\admin\\Desktop\\out-1.json";

            // 会自动关闭流操作，不用手动关闭
            try(FileOutputStream outputStream = new FileOutputStream(path)) {
                byte[] bytes = jsonStr.getBytes();
                outputStream.write(bytes);
            }
            System.out.println("文件生成成功！");

            // 获取生成文件后的字节流
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            System.out.println(bytes.length);

            // 指定服务器地址和端口 TODO 写在配置文件里面，服务器地址
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 7788;

            // 创建数据报
            DatagramPacket sendPacket = new DatagramPacket(bytes, bytes.length, serverAddress, serverPort);

            // 发送数据报
            clientSocket.send(sendPacket);

            // 关闭UDP套接字
            clientSocket.close();

            // 删除生成的临时文件
//            new File(path).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        DatagramSocket datagramSocket = new DatagramSocket();
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("D:\\Users\\admin\\Desktop\\out-1.json"));
//        byte bytes[] = new byte[2048];
//        byte result[] = new byte[2050];
//        byte[] type = {(byte) 0xE0, (byte) 0x02};
//        byte[] end = "quit".getBytes();
//        while (bufferedInputStream.read(bytes,0, bytes.length) != -1){
//            // 接口信息类型
//            System.arraycopy(type, 0, result, 0, type.length);
//            System.arraycopy(bytes, 0, result, 2, bytes.length);
//            datagramSocket.send(new DatagramPacket(result,0,result.length,InetAddress.getByName("127.0.0.1"),7788));
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.arraycopy(type, 0, result, 0, type.length);
//        System.arraycopy(end, 0, result, 2, end.length);
//        datagramSocket.send(new DatagramPacket(result,0,result.length,InetAddress.getByName("127.0.0.1"),7788));
//
//        datagramSocket.close();
    }
}
