package com.lhh.streamApi;



import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 * */
public class TestStreamAPi3 {

    /**
     * 查找与匹配
     * allmatch-检查是否匹配所有元素
     * anymatch-检查是否至少匹配一个元素
     * nonematch-检查是否没有匹配所有元素
     * findFirst-返回第一个元素
     * count- 返回流中的元素的总个数
     * max-返回流中的最大值
     * min-返回流中的最小值
     *
     * */
    List<Employee> employees = Arrays.asList(
            new Employee(101,"张三",20,8000, Employee.Status.VOCATION),
            new Employee(102,"李四",21,6500, Employee.Status.BUSY),
            new Employee(103,"王五",19,9000, Employee.Status.FREE),
            new Employee(104,"赵六",21,7000, Employee.Status.BUSY),
            new Employee(105,"田七",30,10000, Employee.Status.FREE)
    );

    @Test
    public void test1() {
        boolean b = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);


        boolean b1 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println(b1);


        boolean b2 = employees.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println(b2);

        Optional<Employee> first = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(first.get());


        Optional<Employee> any = employees.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(any.get());
    }

    @Test
    public void Yes() {

        Double collect1 = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect1);

        Double collect = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect);

        Optional<Employee> collect2 = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(collect2.get());


        Optional<Double> collect3 = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(collect3.get());

        Map<Employee.Status, List<Employee>> collect4 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect4);

        Map<Employee.Status, Map<String, List<Employee>>> collect5 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((employee -> {
                    if (employee.getAge() > 20) {
                        return "老年人";
                    } else if (employee.getAge() < 20) {
                        return "正常人";
                    } else {
                        return "反人类";
                    }
                }))));
        System.out.println(collect5);

        // 分区
        Map<Boolean, List<Employee>> collect6 = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        System.out.println(collect6);

    }

    @Test
    public void zuoye() {

    }

    @Test
    public void test2() {
        long count = employees.stream()
                .count();
        System.out.println(count);

        Optional<Employee> max = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());

        Optional<Employee> min = employees.stream()
                .min((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println(min.get());

        Optional<Double> min1 = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(min1.get());
    }

    /**
     * 规约与收纳
     * reduce 将流中的元素反复结合起来得到一个值
     *
     * */
    @Test
    public void test3() {
         List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer reduce = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        System.out.println("--------------------------------------");

        // 计算工资的总和 有可能为空的才会被封装到Optional中去
        Optional<Double> reduce1 = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce1.get());
    }
    /**
     * 收集
     * collect-将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法。
     * */
    @Test
    public void test4() {
        List<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        collect.stream().forEach(System.out::println);

        System.out.println("----------------------------------------");

        Set<String> collect2 = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        collect2.stream().forEach(System.out::println);

        System.out.println("-----------------------------------------");

        HashSet<String> collect1 = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

        collect1.stream().forEach(System.out::println);

    }

    @Test
    public void test5() {
        // 总数
        Long collect = employees.stream()
                .collect(Collectors.counting());
        System.out.println(collect);

        System.out.println("------------------------------------");


        // 平均值
        Double collect1 = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect1);

        System.out.println("------------------------------------");


        // 获取到工资到总和
        Double collect2 = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect2);

        System.out.println("-------------------------------------");


        // 获取到工资最多到那个对象
        Optional<Employee> collect3 = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(collect3.get());

        // 获取到最小值
        Optional<Double> collect4 = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(collect4.get());

    }

    @Test
    public void test6() {
        // 按照状态进行分组
        Map<Employee.Status, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
    }

    //多级分组
    @Test
    public void test7() {
        Map<Employee.Status, Map<String, List<Employee>>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() > 20) {
                        return "成年人";
                    } else if (e.getAge() > 30) {
                        return "中年人";
                    } else {
                        return "老年人";
                    }
                })));
        System.out.println(collect);
    }

    //分片，分区
    @Test
    public void test8() {
        Map<Boolean, List<Employee>> collect = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        System.out.println(collect);
    }

    @Test
    public void test9() {
        // 字符串连接
        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(str);

    }

    @Test
    public void test10() {
        DoubleSummaryStatistics collect = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getMax());
        System.out.println(collect.getMin());
        System.out.println(collect.getAverage()) ;
    }
}
