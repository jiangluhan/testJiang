package com.quartz.dynamic.state.enums;

public enum EnumTaskEnable {

    START("2", "开启"),
    STOP("0", "关闭");

    private String code;

    private String msg;

    EnumTaskEnable(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }
}