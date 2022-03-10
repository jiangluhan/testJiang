package com.example.demo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *@description: User 实体类
 *@author: yinkai
 *@create: 2020/2/25 9:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Users {
    private Integer userId;

    private String username;

    private Integer money;
}