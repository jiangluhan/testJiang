package com.quartz.dynamic.state.comm;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private Object retData;
}