package com.syj.myshop.msg;

import lombok.Data;

@Data
public class R <T>{

    int code;
    String msg;
    T data;
}
