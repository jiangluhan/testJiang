package com.selectKey.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jiangluhan
 * @Description:
 * @date 2022/3/2 16:59
 */
@Data
public class testSelectKeyAutoPo {
    // 主键，自增
    private Integer id;

    // 用户名
    @NotBlank(message = "用户名不能为空")
    private String userName;

    private String attr;
}
