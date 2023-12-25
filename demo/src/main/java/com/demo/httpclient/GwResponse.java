package com.demo.httpclient;

import java.io.Serializable;

public class GwResponse<T> implements Serializable {
    // 状态码
    private Integer code = 0;
    // 信息
    private String message = "success";
    // 数据体
    private T data;

    public GwResponse() {
    }

    /**
     * 返回异常
     *
     * @param errorCode 异常码
     * @param msg       异常消息
     * @return
     */
    public static <T> GwResponse<T> exception(int errorCode, String msg) {
        return new GwResponse<T>(errorCode, msg);
    }

    /**
     * 返回，根据返回码返回
     *
     * @param resultCode 返回码
     * @return
     */
//    public static <T> GwResponse<T> getResultByResultCode(ResultCode resultCode) {
//        GwResponse<T> result = new GwResponse<T>();
//        result.setCode(resultCode.getCode());
//        result.setMessage(resultCode.getMsg());
//        return result;
//    }

    /**
     * 返回，根据返回码返回
     *
     * @param resultCode 返回码
     * @param data       数据体
     * @return
     */
//    public static <T> GwResponse<T> getResultByResultCode(ResultCode resultCode, T data) {
//        GwResponse<T> result = new GwResponse<T>();
//        result.setCode(resultCode.getCode());
//        result.setMessage(resultCode.getMsg());
//        result.setData(data);
//        return result;
//    }

    /**
     * 返回成功
     *
     * @return
     */
    public static GwResponse success() {
        GwResponse result = new GwResponse();
        result.setCode(0);
        result.setMessage("success");
        return result;
    }

    /**
     * 返回成功
     *
     * @param data 数据
     * @return
     */
    public static <T> GwResponse<T> success(T data) {
        GwResponse<T> result = new GwResponse<T>();
        result.setCode(0);
        result.setMessage("success");
        result.setData(data);
        return result;
    }


    public GwResponse(Integer status, String message) {
        this.code = status;
        this.message = message;
    }


    public GwResponse(Integer status, String message, T data) {
        this.code = status;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "status=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


}