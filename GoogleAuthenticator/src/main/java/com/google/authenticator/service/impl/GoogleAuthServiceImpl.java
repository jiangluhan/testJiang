package com.google.authenticator.service.impl;

import com.google.authenticator.auth.GoogleAuthenticator;
import com.google.authenticator.dao.GoogleAuthDao;
import com.google.authenticator.po.GoogleAuthPo;
import com.google.authenticator.properties.InitUserProperties;
import com.google.authenticator.service.GoogleAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class GoogleAuthServiceImpl implements GoogleAuthService {

    @Autowired
    private InitUserProperties initUserProperties;

    @Autowired
    private GoogleAuthDao googleAuthDao;

    /**
     * 初始化用户
     */
    @Override
    public void initUser() {
        // 查询用户
        List<GoogleAuthPo> googleAuthPos = googleAuthDao.getUserList();
        if(CollectionUtils.isEmpty(googleAuthPos)) {
            // 获取密钥
            String secretKey = GoogleAuthenticator.generateSecretKey();
            GoogleAuthPo googleAuthPo = new GoogleAuthPo();
            googleAuthPo.setUserName(initUserProperties.getUserName())
                    .setPassword(initUserProperties.getPassword())
                    .setSecretKey(secretKey);
            // 新增用户
            googleAuthDao.initUser(googleAuthPo);
            log.info("用户初始化成功！");
        }
    }

    @Override
    public GoogleAuthPo getUserById(Integer userId) {
        return googleAuthDao.getUserById(userId);
    }
}
