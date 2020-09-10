package com.dw.sc.common.exception;

import com.dw.sc.common.enums.ResultEnum;
import lombok.Getter;

@Getter
public class BusiException extends RuntimeException {

    public BusiException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusiException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusiException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public BusiException(ResultEnum resultEnum, Object... args) {
        super(String.format(resultEnum.getMessage(), args));
        this.code = resultEnum.getCode();
    }

    public BusiException(ResultEnum resultEnum, Throwable cause) {
        super(resultEnum.getMessage(), cause);
        this.code = resultEnum.getCode();
    }

    private int code;

}
