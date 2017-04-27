package com.leimbag.demo.gateway.bean.response;

import java.io.Serializable;

public class ResponseMessage<T extends ResponseMessageBody> implements Serializable {
    private static final long serialVersionUID = -5200281741567280545L;

    private Integer code = 0;
    private String message = "ok";
    private String timestamp = String.valueOf(System.currentTimeMillis());
    private T data;

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
