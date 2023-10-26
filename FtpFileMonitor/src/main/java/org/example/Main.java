package org.example;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // 连接ftp
        String ftpServer = "192.168.1.124";
        int ftpPort = 21;
        String ftpUsername = "hddataftp";
        String ftpPassword = "gwsj@123";

        FTPClient ftpClient = new FTPClient();
        // 连接FTP服务器
        ftpClient.connect(ftpServer, ftpPort);
        // ftp登录
        ftpClient.login(ftpUsername, ftpPassword);
        // ftp端口的打开和关闭
        ftpClient.enterLocalPassiveMode();
        // 设置传输文件类型
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

    }
}