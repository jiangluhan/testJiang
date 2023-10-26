package org.example;

import org.apache.commons.net.ftp.FTPFile;

public interface FTPService {

    /**
     * ftp登陆
     * @return boolean 是否登陆成功
     * */
    boolean login();

    /**
     * ftp登出
     * @return boolean 是否登出成功
     * */
    boolean loginOut();

    /**
     * 获取文件列表
     * @return FTPFile[] 文件列表
     * */
    FTPFile[] listFile();

    /**
     * 监听文件夹的改变
     * @param fileChangeEvent 文件改变事件
     * */
    void addListenerFileChange(FileChangeEvent fileChangeEvent);
}
