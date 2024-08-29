package com.syj.myshop.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements IErrorCode {

    SYSTEM_ERROR(500, "系统错误"),
    ILLEGAL_ARGUMENT(9002, "参数校验失败"),
    OTHER_BUSINESS_ERROR(9000, "todo"),

    CLINT_ERROR(10001, "客户端信息错误"),
    TOKEN_ERROR (10005,"Token错误"),
    GET_TOKEN_ERROR (10010,"获取token错误"),
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
