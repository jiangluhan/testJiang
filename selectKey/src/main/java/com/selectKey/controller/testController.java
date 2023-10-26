package com.selectKey.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class testController {
    @RequestMapping(value = "/v1/testFile", method = {RequestMethod.POST})
    public void test(@RequestParam(value = "file", required = false) MultipartFile file,
                     @RequestParam(value = "testName2", required = false) String testName,
                     @RequestHeader(value = "tenantId") String tenantId) throws Exception {
        long size = file.getSize();
        System.out.println(size);
        System.out.println(file.getOriginalFilename());
        System.out.println(testName);
        System.out.println(tenantId);
    }
}
