package com.leimbag.demo.gateway.exception;

public class GatewayException extends RuntimeException {

    private static final long serialVersionUID = 2521612094747810330L;

    private Integer errorCode;

    public GatewayException() {
        this("gateway exception");
    }

    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(Integer errorCode, String message) {
        this(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
