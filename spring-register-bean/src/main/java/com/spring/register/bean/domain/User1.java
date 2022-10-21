package com.spring.register.bean.domain;

import lombok.Data;

/**
 * 仅用于测试context.getBeanNamesForType(User.class) 是否会获取多个bean
 */
@Data
public class User1 {
    private String userName;

    private String sex;
}
