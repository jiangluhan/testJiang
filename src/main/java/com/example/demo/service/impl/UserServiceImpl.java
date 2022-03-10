package com.example.demo.service.impl;

import com.example.demo.po.User;
import com.example.demo.po.UserCopy;
import com.example.demo.service.UserCopyService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jiangluhan
 * @Description:
 * @date 2021/10/19 16:26
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserCopyService userCopyService;

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void A(User user) {

        System.out.println("这是A方法");
//
//        try{
//            // A调用B
//            userServiceImpl.B(user);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

        // A调用B
        //userServiceImpl.B(user);

        // A调用C
        userServiceImpl.C(user);



//        try{
//            jdbcTemplate.update("INSERT INTO user(userId, username, money) VALUES (?, ?, ?);", user.getUserId(), user.getUsername(), user.getMoney());
//        }catch (Exception e){
//            log.info("A方法出现了异常");
//            e.printStackTrace();
//        }


//        try{
//
//            userServiceImpl.C(user);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
        // A调用B
        //userServiceImpl.B(user);




        UserCopy userCopy = new UserCopy(user.getUserId(), user.getUsername(), user.getMoney());

        //打印事务名
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT TRX_ID FROM INFORMATION_SCHEMA.INNODB_TRX WHERE TRX_MYSQL_THREAD_ID = CONNECTION_ID( );");
        System.out.println(maps + TransactionSynchronizationManager.getCurrentTransactionName());



        //jdbcTemplate.update("INSERT INTO user(userId, username, money) VALUES (?, ?, ?);", user.getUserId(), user.getUsername(), user.getMoney());
//

//        try {
//            // A调用D
//            userCopyService.D(userCopy);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        //jdbcTemplate.update("update user set money = money + 2222 where userId = 1");
        jdbcTemplate.update("INSERT INTO user(userId, username, money) VALUES (?, ?, ?);", user.getUserId(), user.getUsername(), user.getMoney());


        //userCopyService.D(userCopy);
//        try{
//            jdbcTemplate.update("INSERT INTO user(userId, username, money) VALUES (?, ?, ?);", user.getUserId(), user.getUsername(), user.getMoney());
//        }catch (Exception e){
//            log.info("A方法出现了异常");
//            e.printStackTrace();
//        }
        // A调用D

//        try {
//            // A调用D
//            userCopyService.D(userCopy);
//        }catch (Exception e){
//            e.printStackTrace();
//        }




        System.out.println("测试");

    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void B(User user) {

        System.out.println("这是public B方法");

        // 不捕捉异常
        jdbcTemplate.update("INSERT INTO user_copy(userId, username, money) VALUES (?, ?, ?);", user.getUserId(), user.getUsername(), user.getMoney());

        //jdbcTemplate.update("update user_copy set money = money + 9999 where userId = 1");

        // 手动捕捉异常
//        try{
//        	jdbcTemplate.update("INSERT INTO user_copy(userId, username, money) VALUES (?, ?, ?);", user.getUserId(), user.getUsername(), user.getMoney());
//    	}catch(Exception e) {
//            log.info("B方法出现了异常");
//            e.printStackTrace();
//    	}

        //打印事务名
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT TRX_ID FROM INFORMATION_SCHEMA.INNODB_TRX WHERE TRX_MYSQL_THREAD_ID = CONNECTION_ID( );");
        System.out.println(maps + TransactionSynchronizationManager.getCurrentTransactionName());


    }


    @Transactional(propagation = Propagation.REQUIRED)
    private void C(User user) {

        System.out.println("这是private C方法");

        //jdbcTemplate.update("update user_copy set money = money + 9999 where userId = 1");
        System.out.println(user.getUserId() + user.getUsername() + user.getMoney());

        System.out.println(jdbcTemplate);

        jdbcTemplate.update("INSERT INTO user_copy(userId, username, money) VALUES (?, ?, ?);", user.getUserId(), user.getUsername(), user.getMoney());

//        try{
//            jdbcTemplate.update("INSERT INTO user1(userId, username, money) VALUES (?, ?, ?);", user.getUserId(), user.getUsername(), user.getMoney());
//        }catch (Exception e){
//            log.info("C方法出现了异常");
//            e.printStackTrace();
//        }

        //打印事务名
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT TRX_ID FROM INFORMATION_SCHEMA.INNODB_TRX WHERE TRX_MYSQL_THREAD_ID = CONNECTION_ID( );");
        System.out.println(maps + TransactionSynchronizationManager.getCurrentTransactionName());


    }

































//    @Override
//    @Transactional
//    public void transfer(String fromName, String toName, Integer money) {
//        userDao.out(fromName,money);
//        int x= 10;
//        if(x == 10) {
//            throw new RuntimeException("出错了！");
//        }
//        userDao.in(toName,money);
//    }




    /* 验证传播特性 NEVER 开始 */

    // 1.PROPAGATION_NEVER：没有就非事务执行，有就抛出异常
//    @Override
//    @Transactional
//    public void laoda(String fromName, String toName, Integer money) {
//        System.out.println("这是老大的方法");
//        userDao.out(fromName,money); // 老大转出钱
//        try {
//            // 用service是为了避免spring中aop会把这个方法给切掉
//            userService.laoer(toName,money); // 小弟收入钱
//        }catch (RuntimeException e){
//            e.printStackTrace();
//        }
//        int x = 10;
//        if(x == 10) {
//            throw new RuntimeException("老大方法抛异常了！！！");
//        }
//
//        // 出现异常
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.NEVER)
//    public void laoer(String toName, Integer money) {
//        System.out.println("这是老二的方法");
//        userDao.in(toName,money);
//        int x = 10;
//        if(x == 10) {
//            throw new RuntimeException("老二方法抛异常了！！！");
//        }
//    }
    /* 验证传播特性 NEVER 结束 */



//    /* 验证传播特性 NOT_SUPPORTED 开始 */
//    @Override
//    @Transactional
//    public void laoda(String fromName, String toName, Integer money) {
//        System.out.println("这是老大的方法");
//        userDaoCopy.out(fromName,money); // 老大转出钱
//        System.out.println("老大转出钱成功");
//        try {
//            userService.laoer(toName,money); // 小弟收入钱
//        }catch (RuntimeException e){
//            e.printStackTrace();
//        }
//        int x = 10;
//        if(x == 10) {
//            throw new RuntimeException("老大方法抛异常了！！！");
//        }
//
//        // 出现异常
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    public void laoer(String toName, Integer money) {
//        System.out.println("这是老二的方法");
//        userDaoCopy.in(toName,money);
//        System.out.println("老二收入钱成功");
//        int x = 10;
//        if(x == 10) {
//            throw new RuntimeException("老二方法抛异常了！！！");
//        }
//    }

    /* 验证传播特性 NOT_SUPPORTED 结束 */

    /* 验证传播特性 SUPPORTS 开始 */
//    @Override
//    //@Transactional
//    public void laoda(String fromName, String toName, Integer money) {
//        System.out.println("这是老大的方法");
//        userDao.out(fromName,money); // 老大转出钱
//        try {
//            userService.laoer(toName,money); // 小弟收入钱
//        }catch (RuntimeException e){
//            e.printStackTrace();
//        }
//        int x = 10;
//        if(x == 10) {
//            throw new RuntimeException("老大方法抛异常了！！！");
//        }
//
//        // 出现异常
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.SUPPORTS)
//    public void laoer(String toName, Integer money) {
//        System.out.println("这是老二的方法");
//        userDao.in(toName,money);
//        int x = 10;
//        if(x == 10) {
//            throw new RuntimeException("老二方法抛异常了！！！");
//        }
//    }

    /* 验证传播特性 SUPPORTS 结束 */

//    /* 验证传播特性 REQUIRES_NEW 开始 */
//
//    @Override
//    @Transactional
//    public void laoda(String fromName, String toName, Integer money) {
//        System.out.println("这是老大的方法");
//        userDaoCopy.out(fromName,money); // 老大转出钱
//        try {
//            userService.laoer(toName,money); // 小弟收入钱
//        }catch (RuntimeException e){
//            e.printStackTrace();
//        }
//        int x = 10;
//        if(x == 10) {
//            throw new RuntimeException("老大方法抛异常了！！！");
//        }
//
//        // 出现异常
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void laoer(String toName, Integer money) {
//        System.out.println("这是老二的方法");
//        userDaoCopy.in(toName,money);
//        int x = 9;
//        if(x == 10) {
//            throw new RuntimeException("老二方法抛异常了！！！");
//        }
//    }
//
//    /* 验证传播特性 REQUIRES_NEW 结束 */

    /* 验证传播特性 NESTED 开始 */
//
//    @Override
//    @Transactional
//    public void laoda(String fromName, String toName, Integer money) {
//        System.out.println("这是老大的方法");
//        userDao.out(fromName,money); // 老大转出钱
//        try {
//            userService.laoer(toName,money); // 小弟收入钱
//        }catch (RuntimeException e){
//            e.printStackTrace();
//        }
//        int x = 10;
//        if(x == 10) {
//            throw new RuntimeException("老大方法抛异常了！！！");
//        }
//
//        // 出现异常
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.NESTED)
//    public void laoer(String toName, Integer money) {
//        System.out.println("这是老二的方法");
//        userDao.in(toName,money);
//        int x = 10;
//        if(x == 9) {
//            throw new RuntimeException("老二方法抛异常了！！！");
//        }
//    }

    /* 验证传播特性 NESTED 结束 */


//    /* 验证传播特性 REQUIRED 开始 */
//    @Override
//    @Transactional
//    public void laoda(String fromName, String toName, Integer money) {
//        System.out.println("这是老大的方法");
//        userDao.out(fromName,money); // 老大转出钱
//        try {
//            laoer(toName,money); // 小弟收入钱
//        }catch (RuntimeException e){
//            e.printStackTrace();
//        }
//        //laoer(toName,money); // 小弟收入钱
//        int x = 10;
//        if(x == 10) {
//            throw new RuntimeException("老大方法抛异常了！！！");
//        }
//    }
//
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void laoer(String toName, Integer money) {
//        System.out.println("这是老二的方法");
//        userDao.in(toName,money);
//        int x = 9;
//        if(x == 10) {
//            throw new RuntimeException("老二方法抛异常了！！！");
//        }
//    }

////    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    private void laoer(String toName, Integer money) {
//        System.out.println("这是老二的方法");
//        userDao.in(toName,money);
//        int x = 9;
//        if(x == 10) {
//            throw new RuntimeException("老二方法抛异常了！！！");
//        }
//    }

    /* 验证传播特性 REQUIRED 结束 */

    /* 验证传播特性 MANDATORY 开始 */
//    @Override
//    @Transactional
//    public void laoda(String fromName, String toName, Integer money) {
//        System.out.println("这是老大的方法");
//        userDao.out(fromName,money); // 老大转出钱
//        try {
//            userService.laoer(toName,money); // 小弟收入钱
//        }catch (RuntimeException e){
//            e.printStackTrace();
//        }
//        int x = 10;
//        if(x == 10) {
//            throw new RuntimeException("老大方法抛异常了！！！");
//        }
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.MANDATORY)
//    public void laoer(String toName, Integer money) {
//        System.out.println("这是老二的方法");
//        userDao.in(toName,money);
//        int x = 9;
//        if(x == 10) {
//            throw new RuntimeException("老二方法抛异常了！！！");
//        }
//    }

    /* 验证传播特性 MANDATORY 结束 */

}
