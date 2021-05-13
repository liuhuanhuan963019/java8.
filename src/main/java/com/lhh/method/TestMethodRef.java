package com.lhh.method;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/13 8:30
 */

import com.lhh.lamdba.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用：若Lamdba体中的内容有方法已经实现了，我们可以使用“方法引用”
 *          （可以理解为方法引用是Lamdba表达式的另外一种表现形式）
 *主要有三种语法格式：
 *  对象::实例方法名
 *  类::静态方法名
 *  类::实例方法名
 *
 *  注意事项：
 *  1.Lamdba体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 *  2.若Lamdba参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 *
 *  二、构造器引用
 *
 *  格式：
 *  className::new
 *  注意：需要调用的构造器的参数列表需要与函数式接口抽象方法的参数列表保持一致
 *
 *  三：数组引用
 *  Type[]::new
 * */
public class TestMethodRef {

    //对象::实例方法名
    @Test
    public void test() {
        PrintStream ps1 = System.out;

        Consumer<String> con = (x) -> ps1.print(x);

        // 使用方法引用的方式，参数和返回值与lamdba体中的内容是一致的
        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;
        Consumer<String> con2 = System.out::println;
        con2.accept("hello world!!!");
    }

    @Test
    public void test2() {
        Employee emp = new Employee();
        Supplier<String> sup = () -> emp.getName();
        String s = sup.get();
        System.out.println(s);

        Supplier<Integer> sup2 = emp::getAge;
        Integer integer = sup2.get();
        System.out.println(integer);
    }

//    类::静态方法名
    @Test
    public void test3() {
        Comparator<Integer> comp = (o1, o2) -> Integer.compare(o1, o2);

        Comparator<Integer> comp2 = Integer::compare;
    }

    //类::实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String> bp = (x1, x2) -> x1.equals(x2);

        BiPredicate<String, String> bp2 = String::equals;
    }


    //构造器引用
    @Test
    public void test5() {
        Supplier<Employee> sup = () -> new Employee();

        // 构造器引用的方式
        Supplier<Employee> sup2 = Employee::new;
        Employee employee = sup2.get();
        System.out.println(employee);
    }

    @Test
    public void test6() {
        Function<Integer, Employee> function = (x) -> new Employee(x);

        //构造器引用的方式
        Function<Integer, Employee> function1 = Employee::new;

    }

    @Test
    public void test7() {
        Function<Integer, String[]> func = (x) -> new String[x];
        String[] apply = func.apply(10);
        System.out.println(apply.length);

        Function<Integer, Integer[]> func2 = Integer[]::new;
        Integer[] apply1 = func2.apply(20);
        System.out.println(apply1.length);
    }
}
