package com.dw.sc.common.jmh;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

public class MyFunc {

    private static List<Integer> nums = Lists.newArrayList();

    // 初始化10000个随机整数
    static {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            nums.add(1000000 + r.nextInt(1000000));
        }
    }

    /**
     * stream
     */
    public static void foreach1() {
        nums.stream().forEach(n -> isPrime(n));
    }

    /**
     * parallelStream
     */
    public static void foreach2() {
        nums.parallelStream().forEach(n -> isPrime(n));
    }

    /**
     * 判断是否质数
     * @param num
     * @return
     */
    private static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
