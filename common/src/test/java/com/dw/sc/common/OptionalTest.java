package com.dw.sc.common;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {

        // 1.空对象
        Optional<String> opt1 = Optional.empty();
        // 2.不为空对象
        Optional<String> opt2 = Optional.of("Optional");
        // 3.允许为空对象
        Optional<String> opt3 = Optional.ofNullable(null);

//        // 抛异常
//        System.out.println(opt1.get());
//        // 正常返回
//        System.out.println(opt2.get());
//        // 抛异常
//        System.out.println(opt3.get());

//        // false
//        System.out.println(opt1.isPresent());
//        // true
//        System.out.println(opt2.isPresent());
//        // false
//        System.out.println(opt3.isPresent());

//        // 不输出
//        opt1.ifPresent(s -> System.out.println(s));
//        // 输出Optional
//        opt2.ifPresent(s -> System.out.println(s));
//        // 不输出
//        opt3.ifPresent(s -> System.out.println(s));

//        // false
//        System.out.println(Optional.ofNullable(opt1).filter(s -> "Optional".equals(s.orElse(""))).isPresent());
//        // true
//        System.out.println(Optional.ofNullable(opt2).filter(s -> "Optional".equals(s.orElse(""))).isPresent());
//        // false
//        System.out.println(Optional.ofNullable(opt3).filter(s -> "Optional".equals(s.orElse(""))).isPresent());

//        // 输出Null
//        System.out.println(Optional.ofNullable(opt1).map(s -> s.orElse("Null")).get());
//        // 输出Optional
//        System.out.println(Optional.ofNullable(opt2).map(s -> s.orElse("Null")).get());
//        // 输出Null
//        System.out.println(Optional.ofNullable(opt3).map(s -> s.orElse("Null")).get());
//
//        // 输出Null
//        Optional<String> opt4 = Optional.ofNullable(opt1).flatMap(o -> Optional.ofNullable(o.orElse("NULL")));
//        System.out.println(opt4.get());

//        // 输出Null
//        System.out.println(opt1.orElse("Null"));
//        // 输出Optional
//        System.out.println(opt2.orElse("Null"));
//        // 输出Null
//        System.out.println(opt3.orElse("Null"));

//        // 抛异常
//        System.out.println(opt1.orElseThrow(() -> new NullPointerException("Null")));
//        // 输出Optional
//        System.out.println(opt2.orElseThrow(() -> new NullPointerException("Null")));
//        // 抛异常
//        System.out.println(opt3.orElseThrow(() -> new NullPointerException("Null")));

    }

}
