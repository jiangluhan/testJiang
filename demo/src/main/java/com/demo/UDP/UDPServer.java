package com.demo.UDP;

import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            // 创建UDP套接字并绑定到指定端口
            DatagramSocket serverSocket = new DatagramSocket(7788);

            System.out.println("服务器已启动，等待客户端连接...");

            // 创建接收数据的缓冲区
            byte[] receiveData = new byte[2048];

            while (true) {
                // 创建接收数据报
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // 接收数据报
                serverSocket.receive(receivePacket);

                // 解析接收到的数据
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("收到消息：" + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
