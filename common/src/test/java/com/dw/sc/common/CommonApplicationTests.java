package com.dw.sc.common;

import java.util.Optional;

public class CommonApplicationTests {

    public static void main(String[] args) {

        String flagStr = null;

        String result1 = Optional.of(flagStr).orElse("Is Empty");
        String result2 = Optional.of(flagStr).orElseGet(() -> "Is Empty");
        String result3 = Optional.of(flagStr).orElseThrow(() -> new NullPointerException("Is Empty"));


    }

}