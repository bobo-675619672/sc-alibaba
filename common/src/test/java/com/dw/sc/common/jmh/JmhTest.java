package com.dw.sc.common.jmh;

import org.openjdk.jmh.annotations.*;

public class JmhTest {

    @Benchmark
    @Warmup(iterations = 1, time = 2)
    @Threads(2)
    @Fork(2)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 2, time = 2)
    @Timeout(time = 60)
    public void testForEach() {
        MyFunc.foreach2();
    }

}
