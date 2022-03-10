package com.example.demo.service.impl;

import com.example.demo.po.UserCopy;
import com.example.demo.service.UserCopyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;

/**
 * @author jiangluhan
 * @Description:
 * @date 2021/10/21 9:48
 */
@Service
@Slf4j
public class UserCopyServiceImpl implements UserCopyService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void D(UserCopy userCopy) {

        System.out.println("这是D方法");

        jdbcTemplate.update("INSERT INTO user_copy(userId, username, money) VALUES (?, ?, ?);", userCopy.getUserId(), userCopy.getUsername(), userCopy.getMoney());

//        try {
//            jdbcTemplate.update("INSERT INTO user_copy(userId, username, money) VALUES (?, ?, ?);", userCopy.getUserId(), userCopy.getUsername(), userCopy.getMoney());
//        }catch (Exception e){
//            log.info("D方法出现了异常");
//            e.printStackTrace();
//        }
    }
}
