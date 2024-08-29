package com.syj.myshop.msg;

import com.syj.myshop.common.IErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.syj.myshop.common.ErrorCode.OTHER_BUSINESS_ERROR;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R <T>{

    int code;
    String msg;
    T data;


    public static <T> R<T> fail(String msg) {
        return new R<T>(OTHER_BUSINESS_ERROR.getCode(), msg,null);
    }

    public static <T> R<T> success(String msg) {
        return new R<T>(200, msg,null);
    }

    public static <T> R<T> data(T data) {
        return new R<T>(HttpServletResponse.SC_OK,"操作成功",data);
    }

    public static <T> R<T> fail(IErrorCode errorCode,T data) {
        return new R<T>(errorCode.getCode(), errorCode.getMsg(),data);
    }

    public static <T> R<T> fail(IErrorCode errorCode ) {
        return new R<T>(errorCode.getCode(), errorCode.getMsg() ,null);
    }

    public static <T> R<T> fail(IErrorCode errorCode,String msg ) {
        return new R<T>(errorCode.getCode(), errorCode.getMsg() +"【" +  msg+"】",null);
    }


}
