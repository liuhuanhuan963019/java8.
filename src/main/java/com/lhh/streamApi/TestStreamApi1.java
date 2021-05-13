package com.lhh.streamApi;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/13 9:12
 */

import com.lhh.lamdba.Employee;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * stream的三个操作步骤
 * 1.创建流
 * 2.中间操作
 * 3.终止操作
 * */
public class TestStreamApi1 {

    //创建stream
    @Test
    public void test1() {
        //1.可以通过collection系列集合提供的stream()(串行流) 或parallelStream()(并行流)
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过Arrays中的静态方法stream()获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        //3.通过stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("a", "b", "c");

        //4.创建无限流
        //迭代方式
        Stream<Integer> stream4 = Stream.iterate(0,(X)->X+2);
        stream4.limit(20).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random())
                .limit(10)
                .forEach(System.out::println);
    }
}
