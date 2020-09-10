package com.dw.sc.common.exception;

public interface MyAssert {

    BusiException newException(Object... args);

    BusiException newException(Throwable t, Object... args);

    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw newException(obj);
        }
    }

    default void assertNotNull(Object obj, Object... args) {
        if (obj == null) {
            throw newException(args);
        }
    }

}
