package com.demo.httpclient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class testController {
    @PostMapping("/fileUpload")
    public void fileUpload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        String url = "http://192.168.1.83:8087/api/classify/upgrade";
//        String response = httpClientFile.getTestHttpClient(file, url);
//        System.out.println(response);
//
//        System.out.println(JSONUtil.toBean(response, GwResponse.class));

//        httpClientFile.uploadFile(url, file);
        long startTime = System.currentTimeMillis();
//        String testHttpClient = httpClientFile.getTestHttpClient(file, url);
        String s = httpClientFile.uploadFile(url, file);
        System.out.println("上传成功！耗时：" + (System.currentTimeMillis() - startTime));
        System.out.println(s);


    }

}
