package org.example;

import jdk.nashorn.internal.objects.annotations.Function;

public interface FileChangeEvent {

    /**
     * 文件发生改变时触发此方法
     * @param fileChangeData 文件发生了改变
     * */
    @Function
    void change(FileChangeData fileChangeData);
}
