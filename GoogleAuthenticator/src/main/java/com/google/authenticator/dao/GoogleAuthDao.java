package com.google.authenticator.dao;

import com.google.authenticator.po.GoogleAuthPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoogleAuthDao {
    void initUser(GoogleAuthPo googleAuthPo);

    GoogleAuthPo getUserById(@Param("userId") Integer userId);

    List<GoogleAuthPo> getUserList();
}
