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

## 四、引用

### 4.1 方法引用

**定义：**若 Lambda 表达式体中的内容已有方法实现，则我们可以使用“方法引用”

语法格式：

* 对象 :: 实例方法
* 类 :: 静态方法
* 类 :: 实例方法

**对象::实例方法**

```java
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
```

**注意：**Lambda 表达实体中调用方法的参数列表、返回类型必须和函数式接口中抽象方法保持一致

**类::静态方法**

```java
//    类::静态方法名
    @Test
    public void test3() {
        Comparator<Integer> comp = (o1, o2) -> Integer.compare(o1, o2);

        Comparator<Integer> comp2 = Integer::compare;
    }
```

**类::实例方法**

```java
    //类::实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String> bp = (x1, x2) -> x1.equals(x2);

        BiPredicate<String, String> bp2 = String::equals;
    }
```

**条件：**Lambda 参数列表中的第一个参数是方法的调用者，第二个参数是方法的参数时，才能使用 ClassName :: Method

### 4.2 构造器引用

格式：ClassName :: new

```java
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
```

**注意：**需要调用的构造器的参数列表要与函数时接口中抽象方法的参数列表保持一致

### 4.3 数组引用

```java
    @Test
    public void test7() {
        Function<Integer, String[]> func = (x) -> new String[x];
        String[] apply = func.apply(10);
        System.out.println(apply.length);

        Function<Integer, Integer[]> func2 = Integer[]::new;
        Integer[] apply1 = func2.apply(20);
        System.out.println(apply1.length);
    }
```

## 五、Stream API

### 5.1 创建

![img](https://img-blog.csdnimg.cn/20200518225358689.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTIyNTU5NQ==,size_16,color_FFFFFF,t_70#pic_center)

Stream的操作步骤：

![img](https://img-blog.csdnimg.cn/20200518225430446.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTIyNTU5NQ==,size_16,color_FFFFFF,t_70#pic_center)

创建流：（的几种方法如下）

```java
/**
 * stream的三个操作步骤
 * 1.创建流
 * 2.中间操作
 * 3.终止操作
 * */
public class TestStreamApi1 {

    //创建stream
    @Test
    public void test1() {
        //1.可以通过collection系列集合提供的stream()(串行流) 或parallelStream()(并行流)
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过Arrays中的静态方法stream()获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        //3.通过stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("a", "b", "c");

        //4.创建无限流
        //迭代方式
        Stream<Integer> stream4 = Stream.iterate(0,(X)->X+2);
        stream4.limit(20).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random())
                .limit(10)
                .forEach(System.out::println);
    }
}
```

### 5.2 筛选、切片

中间操作：

* filter：接收 Lambda ，从流中排除某些元素
* limit：截断流，使其元素不超过给定数量
* skip(n)：跳过元素，返回一个舍弃了前n个元素的流；若流中元素不足n个，则返回一个空流；与 limit(n) 互补
* distinct：筛选，通过流所生成的 hashCode() 与 equals() 取除重复元素

```java
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
```

![img](https://img-blog.csdnimg.cn/20200518225456187.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTIyNTU5NQ==,size_16,color_FFFFFF,t_70#pic_center)

* 内部迭代：迭代操作由 Stream API 完成
* 外部迭代：我们通过迭代器完成

### 5.3 映射

* map：接收 Lambda ，将元素转换为其他形式或提取信息；接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
* flatMap：接收一个函数作为参数，将流中每一个值都换成另一个流，然后把所有流重新连接成一个流

map：

```java
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
```

flatMap：

```java
        stream.forEach((e)->e.forEach(System.out::println));

        Stream<Character> stream1 = list.stream()
                .flatMap(TestStreamApi2::filterCharacter);
        stream1.forEach(System.out::println);  //将整个流全部取出放在一个流中
```

### 5.4 排序

