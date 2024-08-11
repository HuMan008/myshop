package com.syj.myshop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements IErrorCode {

    SYSTEM_ERROR(500, "系统错误"),
    ILLEGAL_ARGUMENT(9002, "参数校验失败"),
    ;
    private final int code;
    private final String msg;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }


}
