package org.example;

import java.io.IOException;

public class Main {
//    public static void main(String[] args) throws IOException {
//        String ftpServer = "192.168.1.124";
//        int ftpPort = 21;
//        String ftpUsername = "hddataftp";
//        String ftpPassword = "gwsj@123";
//        String ftpFolderPath = "/";
//
//
//        FTPClient ftpClient = new FTPClient();
//
//        ftpClient.connect(ftpServer, ftpPort);
//        ftpClient.login(ftpUsername, ftpPassword);
//        ftpClient.enterLocalPassiveMode();
//        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//
//        String directory = ftpClient.printWorkingDirectory();
//        System.out.println(directory);
//        //获取当前工作文件名列表
//        FTPFile[] ftpFiles = ftpClient.listFiles();
//        System.out.println(ftpFiles.length);
//
//
////        File file = FileUtil.file(ftpFolderPath);
////        WatchMonitor watchMonitor = WatchMonitor.create(ftpFolderPath, WatchMonitor.ENTRY_CREATE);
//        WatchMonitor.createAll(ftpFolderPath, new SimpleWatcher() {
//            @Override
//            public void onCreate(WatchEvent<?> event, Path currentPath) {
//                Object obj = event.context();
//                Console.log("创建：{}-> {}", currentPath, obj);
//            }
//
//            @Override
//            public void onModify(WatchEvent<?> event, Path currentPath) {
//                Object obj = event.context();
//                Console.log("修改：{}-> {}", currentPath, obj);
//            }
//
//            @Override
//            public void onDelete(WatchEvent<?> event, Path currentPath) {
//                Object obj = event.context();
//                Console.log("删除：{}-> {}", currentPath, obj);
//            }
//        }).start();
//    }

    public static void main(String[] args) throws IOException {
//        String ftpServer = "192.168.1.124";
//        int ftpPort = 21;
//        String ftpUsername = "hddataftp";
//        String ftpPassword = "gwsj@123";
//        String ftpFolderPath = "/";
//
//        FTPClient ftpClient = new FTPClient();
//        ftpClient.connect(ftpServer, ftpPort);
//        ftpClient.login(ftpUsername, ftpPassword);
//        ftpClient.enterLocalPassiveMode();
//        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//
//        //获取当前工作文件名列表
//        FTPFile[] ftpFiles = ftpClient.listFiles();
//        System.out.println(ftpFiles.length);
//
//        for (FTPFile file : ftpFiles) {
//            System.out.println(String.format("filename:%s,filesize:%s", file.getName(), file.getSize()));
//        }
//
//        ftpService.addListenerFileChange(ftpFile -> {
//            System.out.println(String.format("文件%s被改变了，文件改变类型%s", ftpFile.getFileName(), ftpFile.getEventType().getDesc()));
//        });
    }
}