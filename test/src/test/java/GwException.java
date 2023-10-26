
public class GwException extends RuntimeException {
    private Integer code;
    private Object data;

    public GwException(String message) {
        super(message);
        this.code = 1;
    }

    public GwException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public GwException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public GwException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public GwException(Integer code, String message, Throwable cause, Object data) {
        super(message, cause);
        this.code = code;
        this.data = data;
    }

    public GwException(Integer code, Throwable cause, Object data) {
        super(cause);
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
