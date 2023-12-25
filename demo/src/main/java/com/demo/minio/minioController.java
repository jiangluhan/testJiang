//package com.demo.minio;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RestController
//public class minioController {
//
//    @Autowired
//    private MinIoHandlerService minIoHandlerService;
//
//    @Autowired
//    private MinIoAutoProperties minIoAutoProperties;
//
//    @PostMapping("/fileUploadMinio")
//    public void fileUpload(@RequestParam(value = "file") MultipartFile file) throws IOException {
//        long startTime = System.currentTimeMillis();
//        try {
//            minIoHandlerService.putObject(minIoAutoProperties.getBucket(), file);
//            System.out.println("上传成功！耗时：" + (System.currentTimeMillis() - startTime));
//            System.out.println(minIoHandlerService.checkFileIsExist(file.getOriginalFilename()));
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
