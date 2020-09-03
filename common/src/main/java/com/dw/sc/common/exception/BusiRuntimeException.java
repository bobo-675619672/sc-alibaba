package com.dw.sc.common.exception;

import com.dw.sc.common.enums.ResultEnum;
import lombok.Getter;

@Getter
public class BusiRuntimeException extends RuntimeException {

    public BusiRuntimeException(ResultEnum httpEnum, String ... params) {
        super(String.format(httpEnum.getMsg(), params));
        this.code = httpEnum.getCode();
    }

    public BusiRuntimeException(Exception e, ResultEnum httpEnum, String ... params) {
        super(String.format(httpEnum.getMsg(), params));
        this.code = httpEnum.getCode();
    }

    public BusiRuntimeException(int code, String message) {
        super(message);
        this.code = code;
    }

    private int code;

}
