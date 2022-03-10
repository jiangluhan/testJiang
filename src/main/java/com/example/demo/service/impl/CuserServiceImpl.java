package com.example.demo.service.impl;

import com.example.demo.po.Cuser;
import com.example.demo.service.CuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;

@Service
public class CuserServiceImpl implements CuserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void InsertCuser(Cuser cuser) {
        jdbcTemplate.update("INSERT INTO user_copy(userId, username, money) VALUES (?, ?, ?);", cuser.getUserId(), cuser.getUsername(), cuser.getMoney());
        //打印事务名
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT TRX_ID FROM INFORMATION_SCHEMA.INNODB_TRX WHERE TRX_MYSQL_THREAD_ID = CONNECTION_ID( );");
        System.out.println(maps + TransactionSynchronizationManager.getCurrentTransactionName());
    }
}