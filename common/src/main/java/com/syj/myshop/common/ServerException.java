package com.syj.myshop.common;

public class ServerException extends RuntimeException {

    private final IErrorCode errorCode;


    public ServerException(IErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }

    public ServerException(String message) {
        super(message);
        this.errorCode = ErrorCode.OTHER_BUSINESS_ERROR;

    }

}
