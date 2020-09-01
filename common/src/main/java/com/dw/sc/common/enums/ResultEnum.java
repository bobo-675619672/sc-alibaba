package com.dw.sc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    TOKEN_EMPTY(10001, "Token为空"),
    TOKEN_INVALID(10002, "Token失效"),
    TOKEN_ERROR(10003, "Token解析错误"),
    TOKEN_OCCUPY(10004, "用户设备被占用"),
    TOKEN_BUILD_ERROR(10099, "Token生成失败");

    private int code;
    private String msg;

}
