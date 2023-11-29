package com.google.authenticator.enums;

public enum ResultCode {
    /*成功*/
    SUCCESS(0, "SUCCESS"),
    /*失败*/
    ERROR(1, "ERROR"),

    /*参数为空*/
    PARAM_NULL(10002, "参数为空"),

    /*参数错误*/
    PARAM_ERROR(10003, "参数错误");


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
