package com.lhh.lamdba;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/12 1:36
 */
public class LamdbaPractice {

    List<Employee> employees = Arrays.asList(
        new Employee(101,"张三",20,8000),
        new Employee(102,"李四",21,6500),
        new Employee(103,"王五",19,9000),
        new Employee(104,"赵六",21,7000),
        new Employee(105,"田七",30,10000)
    );

    // 调用Collection.sort()方法，通过定制排序比较两个Employee（先按年龄比，年龄相同按照姓名比）使用Lamdba作为参数传递
    @Test
    public void test1() {
        Collections.sort(employees, (e1,e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        employees.stream().forEach(System.out::println);
    }

    @Test
    public void test2 () {
        String trimString = strHandle("wo d a ", (str) -> str.trim());
        System.out.println(trimString);
        // 声明类 TestLambda，类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值；
        String upperString = strHandle("abcdef", (str) -> str.toUpperCase());
        System.out.println(upperString);

        String splictString = strHandle("asasasasa", (str)-> str.substring(2,4));
        System.out.println(splictString);
    }

    //(1)声明函数式接口，接口中声明抽象方法，String getValue(String str);
    public String strHandle(String st, LamdbaPracticeInterface LP) {
        return LP.getValue(st);
    }

    @Test
    public void test3() {
       longHandle(20L, 30L, (l1, l2) -> l1 + l2);
       longHandle(20L, 30L, (l1, l2) -> l1 * l2);
    }

    //(1)声明函数式接口，接口中声明抽象方法，String getValue(String str);
    public void longHandle(Long l1, Long l2, LamdbaPracticeInterface2<Long, Long> LP) {
        System.out.println(LP.getValue(l1, l2));
    }
}
