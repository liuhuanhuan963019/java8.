package com.lhh.streamApi;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class practice  {
    /**
     * 案例一：给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     * (如：给定【1，2，3，4，5】，返回【1，4，9，16，25】)
     * */

    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1,2,3,4,5);

        List<Integer> collect = list.stream()
                .map((i) -> i*i)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 2.怎么样用map和reduce方法数一数流中有多少个employee呢
     * */
    List<Employee> employees = Arrays.asList(
            new Employee(101,"张三",20,8000, Employee.Status.VOCATION),
            new Employee(102,"李四",21,6500, Employee.Status.BUSY),
            new Employee(103,"王五",19,9000, Employee.Status.FREE),
            new Employee(104,"赵六",21,7000, Employee.Status.BUSY),
            new Employee(105,"田七",30,10000, Employee.Status.FREE)
    );

    @Test
    public void test2() {
        Optional<Integer> reduce = employees.stream()
                .map((e) -> 1)   //每有一个对象返回一个1
                .reduce(Integer::sum);  //然后统计下有总和
        System.out.println(reduce.get());
    }
}
