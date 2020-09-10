package com.dw.sc.common.exception;

public interface BusiExceptionAssert extends IResponseEnum, MyAssert {

    @Override
    default BusiException newException(Object... args) {
        String msg = String.format(this.getMessage(), args);
        return new BusiException(this.getCode(), msg);
    }

    @Override
    default BusiException newException(Throwable t, Object... args) {
        String msg = String.format(this.getMessage(), args);
        return new BusiException(this.getCode(), msg, t);
    }

}
