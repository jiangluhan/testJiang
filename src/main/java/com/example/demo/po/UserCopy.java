package com.example.demo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author jiangluhan
 * @Description:
 * @date 2021/10/19 18:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserCopy {

    private Integer userId;

    private String username;

    private Integer money;
}
