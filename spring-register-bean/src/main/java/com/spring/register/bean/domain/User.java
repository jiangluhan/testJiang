package com.spring.register.bean.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
//@Component
// 注释掉Component注解，测试组件作用域@Scope
@NoArgsConstructor
public class User {

    private String name;

    private Integer age;
}
