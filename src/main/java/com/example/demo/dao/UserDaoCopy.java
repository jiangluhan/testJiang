package com.example.demo.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author jiangluhan
 * @Description:
 * @date 2021/10/19 18:38
 */
public interface UserDaoCopy {
    /**
     * 转出钱
     * @param fromName
     * @param money
     */
    void out(@Param("fromName") String fromName, @Param("money") Integer money);

    /**
     * 转入钱
     * @param toName
     * @param money
     */
    void in(@Param("toName") String toName, @Param("money") Integer money) ;
}
