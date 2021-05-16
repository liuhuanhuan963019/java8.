package com.lhh.streamApi;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/13 9:24
 */

import com.lhh.lamdba.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * stream的三个操作步骤
 * 1.创建流
 * 2.中间操作
 * 3.终止操作
 * */
public class TestStreamApi2 {

    //中间操作
    /**
     *  筛选和切片
     *  filter-接收Lamdba,从流中排除某些元素
     *  limit-截断流，使其元素不超过给定数量
     *  skip(n)-跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流，与limit(n)互补
     *  distinct-筛选，通过流所生成元素的hashcode()和equals()去除重复元素
     * */
    List<Employee> employees = Arrays.asList(
            new Employee(101,"张三",20,8000),
            new Employee(102,"李四",21,6500),
            new Employee(103,"王五",19,9000),
            new Employee(104,"赵六",21,7000),
            new Employee(105,"田七",30,10000),
            new Employee(105,"田七",30,10000),
            new Employee(105,"田七",30,10000),
            new Employee(105,"田七",30,10000)
    );


    //内部迭代：迭代器由StreamAPI完成
    @Test
    public void test1() {
        //中间操作：不会执行任何操作
        Stream<Employee> employeeStream = employees.stream()
                .filter((e) -> e.getAge() > 20);

        //终止操作: 一次性执行全部内容 即“惰性求值”
        employeeStream.forEach(System.out::println);

    }

    //外部迭代
    @Test
    public void test2() {
        Iterator<Employee> it = employees.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void test3() {
        // 只要找到满足条件的数据，就会终止操作，后续的操作就不会再执行，叫短路
        employees.stream()
                .filter((e) -> e.getAge() > 20)
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        // 只要找到满足条件的数据，就会终止操作，后续的操作就不会再执行，叫短路
        employees.stream()
                .filter((e) -> e.getAge() > 20)
                .forEach(System.out::println);
    }

    @Test
    public void test5() {
        // 只要找到满足条件的数据，就会终止操作，后续的操作就不会再执行，叫短路
        employees.stream()
                .filter((e) -> e.getAge() > 20)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }


    /**
     * 映射
     * map 接收lamdba,将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被引用到其他元素上，并将其映射成一个新的元素。
     * flatmap- 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     * */
    @Test
    public void test6() {
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
                .map((str) -> str.substring(0,2))
                .forEach(System.out::println);

        System.out.println("-----------------------------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------");

        Stream<Stream<Character>> stream = list.stream()
                .map(TestStreamApi2::filterCharacter);  //{{a,a,a} {b,b,b,}}

//        Stream<Stream<Character>> stream = list.stream()
//                .map((e) -> filterCharacter(e));

        stream.forEach((e)->e.forEach(System.out::println));

        Stream<Character> stream1 = list.stream()
                .flatMap(TestStreamApi2::filterCharacter);
        stream1.forEach(System.out::println);  //将整个流全部取出放在一个流中
    }


    @Test
    public void test7() {
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

        List list2 = new ArrayList();

        list2.add(11);
        list2.add(22);
        list2.addAll(list);
        System.out.println(list2);
    }


    public static Stream<Character> filterCharacter(String str) {
            List<Character> list = new ArrayList<>();
            for (Character ch : str.toCharArray()) {
                list.add(ch);
            }
            return list.stream();
    }

}
