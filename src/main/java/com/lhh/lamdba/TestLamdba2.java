package com.lhh.lamdba;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/11 23:58
 */

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一：Lamdba 表达式的基础语法：Java8中引入了一个新的操作符“->”该操作符为箭头操作符或lamdba操作符
 *              箭头操作符将lamdba表达式拆分成两部分:
 * 左侧： Lamdba表达式的参数列表
 * 右侧： Lamdba表达式所需要的执行的功能，即Lamdba体
 *  语法格式一：无参数，无返回值
 *      （）->System.out.println("");
 *
 *  语法格式二：一个参数，无返回值
 *      （x）->System.out.println("");
 *  语法格式三：一个参数，小括号可以省略不写
 *       x->System.out.println("");
 *  语法格式四：有两个以上的参数，有并且Lamdba体中有多条语句，有返回值
 *   Comparator<Integer> com = (x1,x2) -> {
 *             System.out.println("函数式接口");
 *             return Integer.compare(x1, x2);
 *         };
 *   语法格式五：若Lamdba体中只有一条语句，那么return和大括号都可以省略不写
 *      Comparator<Integer> com = (x1,x2) -> Integer.compare(x1, x2);
 *
 *   语法格式六：Lamdba表达式的参数列表的数据类型可以省略不写，因为java编译器通过上下文，推断出数据类型，叫做“类型推断”
 *      (x1,x2) -> Integer.compare(x1, x2);
 * 二。Lamdba 表达式需要“函数式接口的支持”
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口，可以使用注解@FunctionalInterface修饰
 * 可以检查是否是函数式接口
 * */
public class TestLamdba2 {

    //语法格式一：无参数，无返回值
    @Test
    public void test1() {
        int num = 0;   // java1.7之前都是final 类型的
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("hello world!!!" + num);
            }
        };
        runnable.run();
        System.out.println("----------------------------");

        Runnable runnable2 = () -> System.out.println("hello world!!!" + num);
        runnable2.run();
    }

//    语法格式二：一个参数，无返回值
    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("lhh");
    }

//    语法格式四：有两个以上的参数，有并且Lamdba体中有多条语句，有返回值
    @Test
    public void test3() {
        Comparator<Integer> com = (x1,x2) -> {
            System.out.println("函数式接口");
            return Integer.compare(x1, x2);
        };
    }

    // 语法格式四：有两个以上的参数，有并且Lamdba体中有多条语句，有返回值 如果只有一条语句的话，那么大括号和return都可以省略不写
    @Test
    public void test4() {
        Comparator<Integer> com = (x1,x2) -> Integer.compare(x1, x2);
    }


    @Test
    public void test5() {
        Integer num = myFunc(100,(x) -> x * x);
        System.out.println(num);

        System.out.println(myFunc(200, (y) -> y+200));
    }

    public Integer myFunc(Integer num, MyFunc mf) {
        return mf.getValue(num);
    }
}
