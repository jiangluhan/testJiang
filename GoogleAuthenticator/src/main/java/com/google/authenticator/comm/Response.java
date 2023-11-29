package com.google.authenticator.comm;

import com.google.authenticator.enums.ResultCode;

import java.io.Serializable;

public class Response<T> implements Serializable {
    // 状态码
    private Integer code = 0;
    // 信息
    private String message = "success";
    // 数据体
    private T data;

    public Response() {
    }

    /**
     * 返回异常
     *
     * @param errorCode 异常码
     * @param msg       异常消息
     * @return
     */
    public static <T> Response<T> exception(int errorCode, String msg) {
        return new Response<T>(errorCode, msg);
    }

    /**
     * 返回，根据返回码返回
     *
     * @param resultCode 返回码
     * @return
     */
    public static <T> Response<T> getResultByResultCode(ResultCode resultCode) {
        Response<T> result = new Response<T>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        return result;
    }

    /**
     * 返回，根据返回码返回
     *
     * @param resultCode 返回码
     * @param data       数据体
     * @return
     */
    public static <T> Response<T> getResultByResultCode(ResultCode resultCode, T data) {
        Response<T> result = new Response<T>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 返回成功
     *
     * @return
     */
    public static Response success() {
        Response result = new Response();
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
    public static <T> Response<T> success(T data) {
        Response<T> result = new Response<T>();
        result.setCode(0);
        result.setMessage("success");
        result.setData(data);
        return result;
    }


    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Response(Integer code, String message, T data) {
        this.code = code;
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
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


}