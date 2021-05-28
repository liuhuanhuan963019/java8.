package com.lhh.test;

import com.lhh.lamdba.Employee;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Test;

import javax.sound.sampled.EnumControl;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/28 10:47
 */
public class test {
    List<Employee> employees = Arrays.asList(
            new Employee(101,"张三",20,8000),
            new Employee(102,"李四",21,6500),
            new Employee(103,"王五",19,9000),
            new Employee(104,"赵六",21,7000),
            new Employee(105,"田七",30,10000)
    );
//    例1 字符串拼接
    @Test
    public void appendStr() {
        List<String> stringList = Arrays.asList("java","number","one");

        String collect = stringList.stream()
                .collect(Collectors.joining("--"));
        System.out.println(collect);

        String collect1 = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining("--"));
        System.out.println(collect1);
    }

    //例题2  for循环
    @Test
    public void forTest() {
        List<String> stringList = Arrays.asList("java","number","one");
        stringList.stream().forEach(System.out::println);
    }

    //例题3 筛选
    @Test
    public void chooseTest() {
        List<Employee> collect = employees.stream()
                .filter((employees) -> employees.getAge() > 20)
                .distinct()
                .skip(2)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    //例题4  数值比较/求和/排序
    @Test
    public void test() {
        String[] str = {"1","1","15","4","5","6","7","8","12","10","11","14"};
        //转为int后求和
        Integer collect = Arrays.stream(str).collect(Collectors.summingInt((s) -> Integer.parseInt(s)));
        System.out.println(collect);

        int sum = Arrays.stream(str).mapToInt(s -> Integer.parseInt(s)).sum();
        System.out.println(sum);

        int reduce = Arrays.stream(str).mapToInt(Integer::parseInt).reduce(0, (m, n) -> m + n);
        System.out.println(reduce);


        //最大值最小值比较器实现
        String s = Arrays.stream(str).max(test::compator).get();
        System.out.printf(s);

        IntSummaryStatistics collect1 = Arrays.stream(str).collect(Collectors.summarizingInt((e) -> Integer.parseInt(e)));
        System.out.println(collect1.getMax());
        System.out.println(collect1.getMin());
        System.out.println(collect1.getCount());

        //排序从大到小
        Arrays.stream(str)
                .sorted(test::compator)
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }

    private static int compator(String x1, String x2) {
        if(Integer.parseInt(x1) < Integer.parseInt(x2)){
            return -1;
        }else if(Integer.parseInt(x1) == Integer.parseInt(x2)){
            return 0;
        }else{
            return 1;
        }
    }

    //去重截取
    @Test
    public void test1() {
        String[] str = {"1","1","15","4","11","6","7","8","12","10","11","14"};

        //去重
        Arrays.stream(str).distinct().forEach(System.out::println);

        System.out.println("-------------------------------------------------");
        //截取前4条数据
        Arrays.stream(str)
                .distinct()
                .limit(4)
                .forEach(System.out::println);

        //跳过前几条数据
        Arrays.stream(str)
                .distinct()
                .skip(4)
                .forEach(System.out::println);
    }

    // limit+skip 实现截取集合  分割集合
    @Test
    public void test3 () {
        int dayNum = 5;
        List<String> strList = Arrays.asList(
                "2","2","2","2","2",
                "4","4","4","4","4",
                "8","8","8","8","8",
                "16","16");
        //总天数
        int sumDay = strList.size();
        //分组
        int week = sumDay%dayNum == 0 ? sumDay/dayNum:sumDay/dayNum+1;


        Stream.iterate(0, n->n+1)
                .limit(10)
                .forEach(System.out::println);  // 作用等同于for循环

        for (Integer n = 0; n< 10; n = n + 1) {
            System.out.println(n);
        }

        Stream.iterate(0,e->e+1)
                .limit(week)
                .parallel()
                .map(num->{
                    Integer collect = strList.stream()
                            .skip(num * dayNum)
                            .limit(dayNum)
                            .collect(Collectors.summingInt(str -> Integer.parseInt(str)));
                    return collect;
                })
        .collect(ArrayList::new,(list,e)->list.add(e),ArrayList::addAll)
        .forEach(System.out::println);
    }




    //数据处理 map,结果收集
    @Test
    public void test4()  {
        //构建数据源
        JSONArray jsonArray = Stream.iterate(0, i -> i + 1)
                .limit(6)
                .map(num -> {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("mc","mc"+num);
                    jsonObject.put("xm","word"+num);
                    return jsonObject;
                })
                .collect(JSONArray::new, (json, obj) -> {
                    json.add(obj);
                }, JSONArray::addAll);
    }

    // 数据收集collect List 转 Map
    @Test
    public  void test18(){
        // list 转map
        Map<String, Double> collect = employees.stream()
                .collect(Collectors.toMap((e) -> e.getName(), e -> e.getSalary()));
        System.out.println(collect);

        // 解决key重复问题
        Map<String, Employee> collect1 = employees.stream()
                .collect(Collectors.toMap(Employee::getName, Function.identity(), (key1, key2) -> key2));
        System.out.println(collect1);

        // map 转list
        collect1.values().stream()
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // map to list
        List<String> list = Arrays.asList("j","a","k","a","l");

        HashMap<Object, Object> collect2 = list.stream()
                .collect(HashMap::new, (map, key) -> map.put(key, key + "!!"), Map::putAll);
        System.out.println(collect2);
    }

    // List 转JSONarray
    @Test
    public  void test20(){
        List<JSONObject> list = new ArrayList<>();
        for (int i = 0; i <4; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("a"+i,22.5*i);
            list.add(jsonObject);
        }

        JSONArray collect = list.stream()
                .collect(JSONArray::new, (theArr, json) -> theArr.add(json), JSONArray::addAll);
        System.out.println(collect);
    }
}
