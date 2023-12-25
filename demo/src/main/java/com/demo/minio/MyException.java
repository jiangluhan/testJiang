package com.demo.minio;

/**
 * @author wang
 * @des 高维自定义异常
 * @date 2021/7/23 16:46
 */
public class MyException extends RuntimeException {
    //异常码
    private Integer code;

    //数据
    private Object data;

    private Object[] params;

    public MyException(String message) {
        super(message);
        this.code = 1;
    }

    public MyException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public MyException(Integer code, String message, Object[] params) {
        super(message);
        this.code = code;
        this.params= params;
    }

    public MyException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public MyException(Integer code, String message, Throwable cause, Object data) {
        super(message, cause);
        this.code = code;
        this.data = data;
    }

    public MyException(Integer code, Throwable cause, Object data) {
        super(cause);
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
