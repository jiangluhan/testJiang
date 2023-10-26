import java.io.Serializable;

public class GwResponse<T> implements Serializable {
    private Integer status = 0;
    private String message = "success";
    private T data;

    public GwResponse() {
    }

    public static <T> GwResponse<T> exception(int errorCode, String msg) {
        return new GwResponse(errorCode, msg);
    }

    public static <T> GwResponse<T> getResultByResultCode(ResultCode resultCode) {
        GwResponse<T> result = new GwResponse();
        result.setStatus(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        return result;
    }

    public static <T> GwResponse<T> getResultByResultCode(ResultCode resultCode, T data) {
        GwResponse<T> result = new GwResponse();
        result.setStatus(resultCode.getCode());
        result.setMessage(resultCode.getMsg());
        result.setData(data);
        return result;
    }

    public static GwResponse success() {
        GwResponse result = new GwResponse();
        result.setStatus(0);
        result.setMessage("success");
        return result;
    }

    public static <T> GwResponse<T> success(T data) {
        GwResponse<T> result = new GwResponse();
        result.setStatus(0);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public GwResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public GwResponse(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "{status=" + this.status + ", message='" + this.message + '\'' + ", data=" + this.data + '}';
    }
}
