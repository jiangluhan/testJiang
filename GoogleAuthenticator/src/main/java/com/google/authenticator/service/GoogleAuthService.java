package com.google.authenticator.service;

import com.google.authenticator.po.GoogleAuthPo;

public interface GoogleAuthService {
    /**
     * 初始化用户
     */
    void initUser();

    GoogleAuthPo getUserById(Integer userId);
}
