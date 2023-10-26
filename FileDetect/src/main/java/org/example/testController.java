package org.example;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class testController {

    @Autowired
    private FTPService ftpService;

    @PostMapping("/test")
    public void test() throws Exception {
        ftpService.login();
        FTPFile[] ftpFiles = ftpService.listFile();
        for (FTPFile file : ftpFiles) {
            System.out.println(String.format("filename:%s,filesize:%s", file.getName(), file.getSize()));
        }
        ftpService.addListenerFileChange(ftpFile -> {
            System.out.println(String.format("文件%s被改变了，文件改变类型%s", ftpFile.getFileName(), ftpFile.getEventType().getDesc()));
        });
    }
}
