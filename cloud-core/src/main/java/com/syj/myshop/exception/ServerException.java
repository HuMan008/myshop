package com.syj.myshop.exception;

public class ServerException extends RuntimeException{

    private final IErrorCode errorCode;


    public ServerException(IErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }


}
