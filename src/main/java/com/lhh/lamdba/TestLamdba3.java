package com.lhh.lamdba;

import org.junit.Test;

import javax.crypto.spec.PSource;
import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/12 5:31
 */
/*
*   java8 内置四大核心函数式接口
*
*   Counsumer<T> 消费型
*       void accept();
*
*   Supplier<T> 供给型接口
*          T get();
*
*   Function(T,R)：函数行接口
*
*   Predicate<T>：断言型接口
*           boolean test(T t)
* */
public class TestLamdba3 {


//    Counsumer<T> 消费型
    @Test
    public void test1() {
        happy(1000, (money) -> System.out.println("消费了"+money));
    }

    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }


//    Supplier<T> 供给型接口
    @Test
    public void test2() {
        List<Integer> numList = getNumList(10, ()->(int)(Math.random()*1000));
        System.out.println(numList);
    }

    //需求：产生指定个数的整数，并放入到集合当中去
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
            List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    //Function(T,R)：函数行接口
    @Test
    public void test3() {
        String lhh = strHandler("lhh", (str) -> str.trim());
        System.out.println(lhh);
    }

    // 需求：用于处理字符串
    public String strHandler(String str, Function<String,String> fun) {
            return fun.apply(str);
    }

    // Predicate<T>：断言型接口
    @Test
    public void test4() {
        List<String> strings = filterStr(Arrays.asList("adsq", "2we", "cs", "Dqdw", "ddwqdqw"), s -> s.endsWith("w"));
        for (String s : strings) {
            System.out.println(s);
        }
    }

    // 需求：将满足条件的字符串放到集合当中去
    public List<String> filterStr(List<String> strings, Predicate<String> predicate) {
            List<String> list = new ArrayList<>();
            for (String str : strings) {
                if (predicate.test(str)) {
                    list.add(str);
                }
            }
            return list;
    }

}
