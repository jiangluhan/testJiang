package com.example.demo.service.impl;

import com.example.demo.po.Cuser;
import com.example.demo.po.Users;
import com.example.demo.service.CuserService;
import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CuserService cuserService;

    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Override
    public void InsertUsers(Users users) {
        jdbcTemplate.update("INSERT INTO user1(userId, username, money) VALUES (?, ?, ?);",users.getUserId(), users.getUsername(), users.getMoney());
        //调用service中另一个方法
        Cuser cuser = new Cuser(users.getUserId(), users.getUsername(), users.getMoney());
        //打印事务名
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT TRX_ID FROM INFORMATION_SCHEMA.INNODB_TRX WHERE TRX_MYSQL_THREAD_ID = CONNECTION_ID( );");
        System.out.println(maps + TransactionSynchronizationManager.getCurrentTransactionName());
        cuserService.InsertCuser(cuser);
        int i = 1;
        if(i == 1) {
            throw new RuntimeException("出现异常了，回滚");
        }
    }
}