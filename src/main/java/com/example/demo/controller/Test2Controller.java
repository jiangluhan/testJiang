package com.example.demo.controller;

import com.example.demo.po.Users;
import com.example.demo.service.UsersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jiangluhan
 * @Description:
 * @date 2021/10/22 11:31
 */
@RestController
public class Test2Controller {
    @Resource
    private UsersService usersService;

    @RequestMapping("/test")
    public void test(){
        Users users = new Users(107,"hello",22);
        usersService.InsertUsers(users);
    }
}
