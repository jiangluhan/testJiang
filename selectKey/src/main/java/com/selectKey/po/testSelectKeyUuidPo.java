package com.selectKey.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jiangluhan
 * @Description:
 * @date 2022/3/2 16:59
 */
@Data
public class testSelectKeyUuidPo {
    // 主键，UUID
    private String id;

    // 用户名
    @NotBlank(message = "用户名不能为空")
    private String userName;
}
