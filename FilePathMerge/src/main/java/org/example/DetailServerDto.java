package org.example;

import lombok.Data;

import java.util.List;

@Data
public class DetailServerDto {
    /**
     * 检测文件路径
     */
    private List<String> dir;

    /**
     * 服务器地址 IP:PORT
     */
    private String serverAddress;

    /**
     * 用户名--在任务合并接口使用
     */
    private String userName;

    /**
     * 密码--在任务合并接口使用
     */
    private String password;

    public DetailServerDto(List<String> dir, String serverAddress, String userName, String password) {
        this.dir = dir;
        this.serverAddress = serverAddress;
        this.userName = userName;
        this.password = password;
    }
}
