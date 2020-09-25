package com.dw.sc.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class CommonApplicationTests {

    public static void main(String[] args) {

        String flagStr = null;

        String result1 = Optional.ofNullable(flagStr).orElse("Is Empty");
        String result2 = Optional.ofNullable(flagStr).orElseGet(() -> "Is Empty");
//        String result3 = Optional.of(flagStr).orElseThrow(() -> new NullPointerException("Is Empty"));

        log.info("result1 : {}", result1);
        log.info("result2 : {}", result2);
//        log.info("result3 : {}", result3);

    }

}