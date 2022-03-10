package com.example.demo.controller;

import com.example.demo.po.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiangluhan
 * @Description:
 * @date 2021/10/19 16:31
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @PostMapping("/test")
    public void test(@RequestBody User user) {
        System.out.println("这是测试类");
        userService.A(user);
    }
}
