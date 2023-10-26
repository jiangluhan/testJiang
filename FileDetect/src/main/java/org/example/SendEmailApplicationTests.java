package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendEmailApplicationTests {
   @Autowired
   private FTPService ftpService;

//   @Test
//   public void ftpTest() {
//        ftpService.login();
//        FTPFile[] ftpFiles = ftpService.listFile();
//        for (FTPFile file : ftpFiles) {
//            System.out.println(String.format("filename:%s,filesize:%s", file.getName(), file.getSize()));
//        }
//        ftpService.addListenerFileChange(ftpFile -> {
//            System.out.println(String.format("文件%s被改变了，文件改变类型%s", ftpFile.getFileName(), ftpFile.getEventType().getDesc()));
//        });
//    }
}