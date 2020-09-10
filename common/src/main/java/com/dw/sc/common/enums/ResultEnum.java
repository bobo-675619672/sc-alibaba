package com.dw.sc.common.enums;

import com.dw.sc.common.exception.BusiExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum implements BusiExceptionAssert {

    /**
     * 10***
     * 参数式系统错误码
     */
    PARAM_EMPTY(10001, "%s为空"),
    PARAM_ERROR(10002, "%s错误"),

    /**
     * 11***
     * 系统错误码
     */
    SYSTEM_ERROR(11001, "系统错误"),
    SYSTEM_EXCEPTION(11002, "系统异常"),

    /**
     * 99***
     * TOKEN相关错误码
     */
    TOKEN_EMPTY(99001, "Token为空"),
    TOKEN_INVALID(99002, "Token失效"),
    TOKEN_ERROR(99003, "Token解析错误"),
    TOKEN_OCCUPY(99004, "用户设备被占用"),
    TOKEN_BUILD_ERROR(99099, "Token生成失败"),

    ;

    private int code;
    private String message;

}
