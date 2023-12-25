package com.demo.minio;

/**
 * @author wang
 * @des 响应码以及响应msg
 * @date 2021/7/23 17:18
 */
public enum ResultCode {
    /*成功*/
    SUCCESS(0, "SUCCESS"),
    /*失败*/
    ERROR(1, "ERROR"),

    /*参数为空*/
    PARAM_NULL(10002, "参数为空"),

    /*参数错误*/
    PARAM_ERROR(10003, "参数错误"),

    /*minio文件系统创建bucket失败*/
    MINIO_CREATE_BUCKET_CODE_ERROR(91000, "minio文件系统创建bucket失败"),
    /*minio文件系统bucket不存在*/
    MINIO_CHECK_BUCKET_EXIST_ERROR(91001, "minio文件系统bucket不存在");


    //响应码
    private int code;
    // 响应消息
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
