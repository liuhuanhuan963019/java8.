package com.lhh.streamApi;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestTransaction {

    List<Transaction> transactions = null;

    @Before
    public void before() {
        Trader raoul = new Trader("Rauol","huaian");
        Trader mario = new Trader("Mario","nanjing");
        Trader alan = new Trader("Alan","suzhou");
        Trader brian = new Trader("Bsrian","huaian");

        transactions = Arrays.asList(
                new Transaction(brian,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2012,950)
        );
    }

    //1.找出2011年发生的所有交易，并按交易额排序（从低到高）
    @Test
    public void test1() {
        transactions.stream()
                .filter((e)->e.getYear()==2011)
                .sorted((e1,e2)->Integer.compare(e1.getValue(),e2.getValue()))
                .forEach(System.out::println);
    }

    //2.交易员都在哪些城市工作过
    @Test
    public void test2() {
        List<String> collect = transactions.stream()
                .map((t) -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    //3.查找所有来自淮安的交易员，并按姓名排序
    @Test
    public void test3() {
        transactions.stream()
                .filter((t)->t.getTrader().getCity().equals("huaian"))
                .sorted((t1,t2)-> t1.getTrader().getName().compareTo(t2.getTrader().getName()))
                .distinct()
                .forEach(System.out::println);

    }

    //4.返回所有交易员的姓名字符串，按字母顺序排序
    @Test
    public void test4() {
        transactions.stream()
                .map((t)->t.getTrader().getName())
                .sorted()
                .distinct()
                .forEach(System.out::println);

        System.out.println("------------------------------------");

        String reduce = transactions.stream()
                .map((t) -> t.getTrader().getName())
                .sorted()
                .reduce("", String::concat);
        System.out.println(reduce);

    }

    //5.有没有交易员是在南京工作的
    @Test
    public void test5() {
        boolean nanjing = transactions.stream()
                .anyMatch((t) -> t.getTrader().getCity().equals("nanjing"));
        System.out.println(nanjing);
    }


    //6.打印生活在淮安的交易员的所有交易额
    @Test
    public void test6() {
        Integer huaian1 = transactions.stream()
                .filter((f) -> f.getTrader().getCity().equals("huaian"))
                .collect(Collectors.summingInt(Transaction::getValue));
        System.out.println(huaian1);


        Optional<Integer> huaian = transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("huaian"))
                .map((t) -> t.getValue())
                .reduce(Integer::sum);  // 将流中元素反复结合起来得到一个值
        System.out.println(huaian.get());
    }

    //7.所有的交易中，最高的交易额是多少
    @Test
    public void test7() {
        Optional<Transaction> collect1 = transactions.stream()
                .collect(Collectors.maxBy((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue())));
        System.out.println(collect1.get().getValue());

        Optional<Integer> collect2 = transactions.stream()
                .map(Transaction::getValue)
                .collect(Collectors.maxBy(Integer::compare));
        System.out.println(collect2.get());


        Optional<Integer> collect = transactions.stream()
                .map((t) -> t.getValue())
                .collect(Collectors.maxBy(Integer::compare));
        System.out.println(collect.get());

        System.out.println("-------------------------------");

        Optional<Transaction> max = transactions.stream()
                .max((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));
        System.out.println(max);
    }

    //8.所有的交易中，最小的交易额是多少
    @Test
    public void test8() {
        Optional<Integer> collect = transactions.stream()
                .map((t) -> t.getValue())
                .collect(Collectors.minBy(Integer::compare));
        System.out.println(collect.get());


        Optional<Integer> min = transactions.stream()
                .map((t) -> t.getValue())
                .min(Integer::compare);
        System.out.println(min.get());
    }
}
