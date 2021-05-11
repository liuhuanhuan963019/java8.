# Java8

## 一、新特性

### 1.1 生态

* **Lambda 表达式**
* 函数式接口
* 方法引用 / 构造器引用
* **Stream API**
* 接口中的默认方法 / 静态方法
* 新时间日期 API
* 其他新特性

### 1.2 新特性

* 速度更快
* 代码更少
* 强大的 Stream API
* 便于并行
* 最大化减少空指针异常 Optional (Kotlin ?)

### 1.3 温故而知新

* Hashmap 底层结构/原理 老话题不再阐述 …
* 并发hashmap …
* Java虚拟机 …
* Java内存模型 …

## 二、Lamdba

### 2.1 匿名函数

 Lambda是一个匿名函数，可以理解为一段可以传递的代码（将代码像数据一样传递）；可以写出更简洁、更灵活的代码；作为一种更紧凑的代码风格，是Java语言表达能力得到提升。

### 2.2 匿名内部类

```java
@Test
public void test01(){
    //匿名内部类
    Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1,o2);
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    };
    //调用
    TreeSet<Integer> set = new TreeSet<>(comparator);
}
```

### 2.3 Lambda

```java
@Test
public void test02(){
    // Lambda 表达式
    Comparator<Integer> comparator = (a, b) -> Integer.compare(a, b);

    TreeSet<Integer> set = new TreeSet<>(comparator);
}
```

演变过程：

> - 垃圾代码 --> 策略模式 --> 匿名内部类 --> Lambda表达式

基础语法：

> - 操作符：->
> - 左侧：参数列表
> - 右侧：执行代码块 / Lambda 体

口诀：

- 写死小括号，拷贝右箭头，落地大括号
- 左右遇一括号省
- 左侧推断类型省

语法格式：

- 无参数，无返回值：() -> sout

例如 Runnable接口：

```java
public class TestLamdba2 {

    @Test
    public void test() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("hello world!!!");
            }
        };
        runnable.run();
        System.out.println("----------------------------");

        Runnable runnable2 = () -> System.out.println("hello world!!!");
        runnable2.run();
    }
}
```

* 有一个参数，无返回值

```java
    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("lhh");
    }
```

* 有一个参数，无返回值 （小括号可以省略不写）

```java
@Test
public void test2() {
    Consumer<String> con = x -> System.out.println(x);
    con.accept("lhh");
}
```

* 有两个及以上的参数，有返回值，并且 Lambda 体中有多条语句

```java
    @Test
    public void test3() {
        Comparator<Integer> com = (x1,x2) -> {
            System.out.println("函数式接口");
            return Integer.compare(x1, x2);
        };
    }
```

* 有两个及以上的参数，有返回值，并且 Lambda 体中只有1条语句 （大括号 与 return 都可以省略不写）

```java
 @Test
    public void test4() {
        Comparator<Integer> com = (x1,x2) -> Integer.compare(x1, x2);
    }
```

* Lambda 表达式 参数的数据类型可以省略不写 Jvm可以自动进行 “类型推断”

函数式接口：

* 接口中只有一个抽象方法的接口 @FunctionalIterface

测试：

* 定义一个函数式接口：

```java
@FunctionalInterface
public interface MyFun {

    Integer count(Integer a, Integer b);
}
```

* 用一下：

```java
@Test
public void test05(){
    MyFun myFun1 = (a, b) -> a + b;
    MyFun myFun2 = (a, b) -> a - b;
    MyFun myFun3 = (a, b) -> a * b;
    MyFun myFun4 = (a, b) -> a / b;
}
```

* 案例教程：

```java
    @Test
    public void test5() {
        Integer num = myFunc(100,(x) -> x * x);
        System.out.println(num);

        System.out.println(myFunc(200, (y) -> y+200));
    }

    public Integer myFunc(Integer num, MyFunc mf) {
        return mf.getValue(num);
    }
```

### 2.4  练习

1、调用Collection.sort()方法，通过定制排序比较两个Employee（先按年龄比，年龄相同按照姓名比）使用Lamdba作为参数传递

```java
ist<Employee> employees = Arrays.asList(
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
```

2、(1)声明函数式接口，接口中声明抽象方法，String getValue(String str);

```java
@FunctionalInterface
public interface LamdbaPracticeInterface {

    public String getValue(String str);
}
```

​	(2)声明类 TestLambda，类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值；

​	(3)再将一个字符串的第二个和第四个索引位置进行截取字串

```java
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
```

3、(1)声明一个带两个泛型的函数式接口，泛型类型为<T, R> T 为参数，R 为返回值；

```java
@FunctionalInterface
public interface LamdbaPracticeInterface2<T,R> {

    public R getValue(T t1,T t2);
}
```

​	 (2)接口中声明对应的抽象方法；

​    (3)在 TestLambda 类中声明方法，使用接口作为参数，计算两个 Long 类型参数的和；

​    (4)在计算两个 Long 类型参数的乘积；

```java
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
```

## 三、函数式接口

Java内置四大核心函数式接口：

| **函数式接口**                | 参数类型 | **返回类型** | **用途**                                                     |
| ----------------------------- | -------- | ------------ | ------------------------------------------------------------ |
| Consumer<br/>消费型接口       | T        | void         | 对类型为T的对象应用操作：void accept(T t)                    |
| Supplier<br/>提供型接口       | 无       | T            | 返回类型为T的对象：T get()                                   |
| Function<T, R><br/>函数型接口 | T        | R            | 对类型为T的对象应用操作，并返回结果为R类型的对象：R apply(T t) |
| Predicate<br/>断言型接口      | T        | boolean      | 确定类型为T的对象是否满足某约束，并返回boolean值：boolean test(T t) |

### 3.1 消费型接口

```java
//    Counsumer<T> 消费型
    @Test
    public void test1() {
        happy(1000, (money) -> System.out.println("消费了"+money));
    }

    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }
```



### 3.2 提供型接口

```java
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
```

### 3.3 函数型接口

```java
@Test
    public void test3() {
        String lhh = strHandler("lhh", (str) -> str.trim());
        System.out.println(lhh);
    }

    // 需求：用于处理字符串
    public String strHandler(String str, Function<String,String> fun) {
            return fun.apply(str);
    }
```

### 3.4 断言型接口

```java
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
```

### 3,5 其他接口

![img](https://img-blog.csdnimg.cn/20200518225311406.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTIyNTU5NQ==,size_16,color_FFFFFF,t_70#pic_center)

