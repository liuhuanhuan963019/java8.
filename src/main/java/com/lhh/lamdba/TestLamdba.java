package com.lhh.lamdba;

import org.junit.Test;

import java.util.*;
/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/12 1:17
 */
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
            new Employee(101,"lhh1",20,11000),
            new Employee(102,"lhh2",21,12000),
            new Employee(103,"lhh3",22,13000),
            new Employee(104,"lhh4",23,14000),
            new Employee(105,"lhh5",24,15000),
            new Employee(106,"lhh6",25,16000)
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


    // 优化方式一：策略设计模式
    public static List<Employee> filterEmployee2(List<Employee> list, Mypredicate<Employee> mp) {
        List<Employee> emps = new ArrayList<>();
        for (Employee e : list) {
            if (mp.test(e)) {
                emps.add(e);
            }
        }
        return emps;
    }


    @Test
    public void test5() {
        List<Employee> emps = filterEmployee2(employees, new FilterEmployeeByAge());
        for (Employee e : emps) {
            System.out.println(e);
        }

        List<Employee> emps2 = filterEmployee2(employees, new FilterEmployeeBySalary());
        for (Employee e : emps2) {
            System.out.println(e);
        }
    }


    //优化方式二：匿名内部类
    @Test
    public void test6() {
        List<Employee> emps = filterEmployee2(employees, new Mypredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 20;
            }
        });

        for (Employee e : emps) {
            System.out.println(e);
        }
    }

    //优化方式三：lamdba表达式
    @Test
    public void test7() {
        List<Employee> emps = filterEmployee2(employees, (e) -> e.getSalary() > 12000);
        emps.forEach(System.out::println);
    }


    //优化方式四：stream API
    @Test
    public void test8() {
        employees.stream()
                .filter((e)->e.getSalary() > 14000)
                .limit(1)
                .forEach(System.out::println);

        System.out.println("-----------------------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
