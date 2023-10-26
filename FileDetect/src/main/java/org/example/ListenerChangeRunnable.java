package org.example;

public interface ListenerChangeRunnable extends Runnable {

    /**
     * 停止监听文件
     * @return boolean 是否停止成功
     * */
    boolean stopListener();
}
