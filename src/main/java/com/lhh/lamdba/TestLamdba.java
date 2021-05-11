package com.lhh.lamdba;

import org.junit.Test;

import java.util.*;

public class TestLamdba {


    // 匿名内部类
    @Test
    public void test1(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return compare(o1,o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
    }


    // 使用lamdba的方式
    @Test
    public void test2(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    // 数组转集合
    List<Employee> employees = Arrays.asList(
            new Employee("lhh1",20,11000),
            new Employee("lhh2",21,12000),
            new Employee("lhh3",22,13000),
            new Employee("lhh4",23,14000),
            new Employee("lhh5",24,15000),
            new Employee("lhh6",25,16000)
    );

    // 过滤大于25的信息
    public List<Employee> filterEmployee(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee e : list) {
            if (e.getAge() > 24) {
                emps.add(e);
            }
        }
        return emps;
    }

    // 需求：获取公司中员工年龄大于35的员工信息
    @Test
    public void test3() {
        List<Employee> emps = filterEmployee(employees);
        for (Employee e: emps) {
            System.out.println(e);
        }
    }

    // 过滤大于25的信息
    public List<Employee> filterEmployee2(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee e : list) {
            if (e.getSalary() > 12000) {
                emps.add(e);
            }
        }
        return emps;
    }

    // 需求：获取公司中员工工资高于12000的员工信息
    @Test
    public void test4() {
        List<Employee> emps = filterEmployee2(employees);
        for (Employee e: emps) {
            System.out.println(e);
        }
    }

}
