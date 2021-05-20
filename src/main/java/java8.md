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

- sorted()：自然排序
- sorted(Comparator c)：定制排序

Comparable：自然排序

```java
@Test
    public void test8(){
        List<String> list = Arrays.asList("bbb","aaa","ccc");

        list.stream()
                .sorted()
                .forEach(System.out::println);

        List<Integer> list2 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        list2.stream()
                .filter(TestStreamApi2::compare)
                .forEach(System.out::println);
    }


    public static boolean compare(Integer i){
        if (i == 1) {
            return true;
        }
        if (i==2)
            return true;

        return false;
    }
```

Comparator：定制排序

```java
    @Test
    public void test9() {
        employees.stream()
                .sorted((e1,e2)->{
                    if(e1.getAge() == e2.getAge()) {
                        return e1.getName().compareTo(e2.getName());
                    }
                    return Integer.compare(e1.getAge(),e2.getAge());
                }).forEach(System.out::println);
    }
```

### 5.5 查找、匹配

终止操作：

- allMatch：检查是否匹配所有元素
- anyMatch：检查是否至少匹配一个元素
- noneMatch：检查是否没有匹配所有元素
- findFirst：返回第一个元素
- findAny：返回当前流中的任意元素
- count：返回流中元素的总个数
- max：返回流中最大值
- min：返回流中最小值

```java
   List<Employee> employees = Arrays.asList(
            new Employee(101,"张三",20,8000, Employee.Status.VOCATION),
            new Employee(102,"李四",21,6500, Employee.Status.BUSY),
            new Employee(103,"王五",19,9000, Employee.Status.FREE),
            new Employee(104,"赵六",21,7000, Employee.Status.BUSY),
            new Employee(105,"田七",30,10000, Employee.Status.FREE)
    );

    @Test
    public void test1() {
        boolean b = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);


        boolean b1 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println(b1);


        boolean b2 = employees.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println(b2);

        Optional<Employee> first = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(first.get());


        Optional<Employee> any = employees.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(any.get());
    }

    @Test
    public void test2() {
        long count = employees.stream()
                .count();
        System.out.println(count);

        Optional<Employee> max = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());

        Optional<Employee> min = employees.stream()
                .min((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println(min.get());

        Optional<Double> min1 = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(min1.get());
    }
```

### 5.6 归约 、收集

- 归约：reduce(T identity, BinaryOperator) / reduce(BinaryOperator) 可以将流中的数据反复结合起来，得到一个值
- 收集：collect 将流转换成其他形式；接收一个 Collector 接口的实现，用于给流中元素做汇总的方法

reduce:

```java
    @Test
    public void test3() {
         List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer reduce = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        System.out.println("--------------------------------------");

        // 计算工资的总和 有可能为空的才会被封装到Optional中去
        Optional<Double> reduce1 = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce1.get());
    }
```

collect:

```java
    @Test
    public void test4() {
        List<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        collect.stream().forEach(System.out::println);

        System.out.println("----------------------------------------");

        Set<String> collect2 = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        collect2.stream().forEach(System.out::println);

        System.out.println("-----------------------------------------");

        HashSet<String> collect1 = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

        collect1.stream().forEach(System.out::println);

    }
    @Test
    public void test5() {
        // 总数
        Long collect = employees.stream()
                .collect(Collectors.counting());
        System.out.println(collect);

        System.out.println("------------------------------------");


        // 平均值
        Double collect1 = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect1);

        System.out.println("------------------------------------");


        // 获取到工资到总和
        Double collect2 = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect2);

        System.out.println("-------------------------------------");


        // 获取到工资最多到那个对象
        Optional<Employee> collect3 = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(collect3.get());

        // 获取到最小值
        Optional<Double> collect4 = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(collect4.get());

    }
 @Test
    public void test6() {
        // 按照状态进行分组
        Map<Employee.Status, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
    }

    //多级分组
    @Test
    public void test7() {
        Map<Employee.Status, Map<String, List<Employee>>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() > 20) {
                        return "成年人";
                    } else if (e.getAge() > 30) {
                        return "中年人";
                    } else {
                        return "老年人";
                    }
                })));
        System.out.println(collect);
    }

    //分片，分区
    @Test
    public void test8() {
        Map<Boolean, List<Employee>> collect = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        System.out.println(collect);
    }

    @Test
    public void test9() {
        // 字符串连接
        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(str);

    }

    @Test
    public void test10() {
        DoubleSummaryStatistics collect = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getMax());
        System.out.println(collect.getMin());
        System.out.println(collect.getAverage()) ;
    }
```

![在这里插入图片描述](https://tva1.sinaimg.cn/large/008i3skNgy1gqo7ct3ntej30vw0hawnd.jpg)

### 5.7 练习

**案例一：**给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？(如：给定【1，2，3，4，5】，返回【1，4，9，16，25】)

```java
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1,2,3,4,5);

        List<Integer> collect = list.stream()
                .map((i) -> i*i)
                .collect(Collectors.toList());
        System.out.println(collect);
    }
```

**案例二：**怎样使用 map 和 reduce 数一数流中有多少个 Employee 呢？

```java
List<Employee> employees = Arrays.asList(
            new Employee(101,"张三",20,8000, Employee.Status.VOCATION),
            new Employee(102,"李四",21,6500, Employee.Status.BUSY),
            new Employee(103,"王五",19,9000, Employee.Status.FREE),
            new Employee(104,"赵六",21,7000, Employee.Status.BUSY),
            new Employee(105,"田七",30,10000, Employee.Status.FREE)
    );

    @Test
    public void test2() {
        Optional<Integer> reduce = employees.stream()
                .map((e) -> 1)   //每有一个对象返回一个1
                .reduce(Integer::sum);  //然后统计下有总和
        System.out.println(reduce.get());
    }
```

**案例三：**题目在代码中

```java
package com.lhh.streamApi;

import java.util.Objects;

public class Trader {

    private String name;

    private String city;

    public Trader() {
    }

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trader)) return false;
        Trader trader = (Trader) o;
        return Objects.equals(name, trader.name) && Objects.equals(city, trader.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
```

```java
package com.lhh.streamApi;

import java.util.Objects;

public class Transaction {

    private Trader trader;

    private int year;

    private int value;

    public Transaction() {
    }

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return year == that.year && value == that.value && Objects.equals(trader, that.trader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trader, year, value);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}

```

```java
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
        Optional<Integer> huaian = transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("huaian"))
                .map((t) -> t.getValue())
                .reduce(Integer::sum);  // 将流中元素反复结合起来得到一个值
        System.out.println(huaian.get());
    }

    //7.所有的交易中，最高的交易额是多少
    @Test
    public void test7() {
        Optional<Integer> collect = transactions.stream()
                .map((t) -> t.getValue())
                .collect(Collectors.maxBy(Integer::compare));
        System.out.println(collect.get());

        System.out.println("-------------------------------");

        Optional<Transaction> max = transactions.stream()
                .max((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));
        System.out.println(max
        );
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
```

### 5.8 并行流

- 并行流：就是把一个内容分成几个数据块，并用不同的线程分别处理每个数据块的流
- Java 8 中将并行进行了优化，我们可以很容易的对数据进行操作；Stream API 可以声明性地通过 parallel() 与 sequential() 在并行流与串行流之间切换

Fork / Join 框架：

![在这里插入图片描述](https://tva1.sinaimg.cn/large/008i3skNgy1gqo7iaefmkj30vv0m0wn0.jpg)

Fork / Join 实现：

```java
package com.lhh.streamApi;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * RecursiveAction 方法没有返回值
 * RecursiveTask方法有返回值
 * */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;

    private long end;

    private static final long THRESHOLD = 10000;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
        else {
            long middle = (start + end) /2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();   //拆分子任务，同时加入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }

    }
}

```

```java
package com.lhh.streamApi;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoin {

    /**
     * fork join框架
     * */
    @Test
    public void test1() {
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0,100000000l);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗时："+ Duration.between(start,end).toMillis());
    }

    @Test
    public void test2() {
        Instant start = Instant.now();

        long sum  = 0L;

        for (long i = 0; i <= 100000000l; i++) {
            sum += i;
        }
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("耗时："+ Duration.between(start,end).toMillis());
    }


    /**
     * java8并行流
     * */
    @Test
    public void test3() {
        Instant start = Instant.now();

        LongStream.rangeClosed(0,100000000l)
                .parallel()
                .reduce(0,Long::sum);

        Instant end = Instant.now();
        System.out.println("耗时："+ Duration.between(start,end).toMillis());
    }
}
```

## 六、Optional

**定义：**Optional 类 (java.util.Optional) 是一个容器类，代表一个值存在或不存在，原来用 null 表示一个值不存在，现在用 Optional 可以更好的表达这个概念；并且可以避免空指针异常

常用方法：

- Optional.of(T t)：创建一个 Optional 实例
- Optional.empty(T t)：创建一个空的 Optional 实例
- Optional.ofNullable(T t)：若 t 不为 null，创建 Optional 实例，否则空实例
- isPresent()：判断是否包含某值
- orElse(T t)：如果调用对象包含值，返回该值，否则返回 t
- orElseGet(Supplier s)：如果调用对象包含值，返回该值，否则返回 s 获取的值
- map(Function f)：如果有值对其处理，并返回处理后的 Optional，否则返回 
- Optional.empty()
- flatmap(Function mapper)：与 map 相似，要求返回值必须是 Optional



```java
package com.lhh.optional;


import com.lhh.streamApi.Employee;
import org.junit.Test;

import java.util.Optional;

public class TestOptional {

    /**
     * optional 容器类的常用方法有：
     * Optional.of(T t): 创建一个optional实例
     * Optional.empty(T t): 创建一个空的optional实例
     * Optional.ofNullable(T t):若t不为null,创建optional实例，否则创建空实例。
     * isPresent()  判断是否有值
     * orElse(T t)  如果调用对象包含值，则返回该值，否则返回T
     * orElseGet(Supplier s) 如果调用对象包含值，则返回该值，否则返回s获取的值
     * map(Function f)   如果有值对其进行处理，并返回处理后的Optional,否则返回Optional.empty()
     * flatMap(Function f) 与mapper类似，要求返回值必须是Optional
     * */

    @Test
    public void test1() {
        // 可以快速定位空指针异常的地方  of方式不能传入null值
        Optional<Employee> employee = Optional.of(new Employee());
        Employee employee1 = employee.get();
        System.out.println(employee1);

    }

    @Test
    public void test2() {
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.get());
    }

    @Test
    public void test3() {
        //两种方式均可，只是传入null时，无法获取到值
//        Optional<Object> empty = Optional.ofNullable(null);
        Optional<Object> empty = Optional.ofNullable(new Employee());
        System.out.println(empty.get());
    }

    @Test
    public void test4() {
        Optional<Employee> empty = Optional.ofNullable(new Employee());

//        if (empty.isPresent()) {
//            System.out.println(empty.get());
//        }
        // 避免了空指针异常的问题
        Employee zhangsan = empty.orElse(new Employee(1, "zhangsan", 2, 10, Employee.Status.BUSY));
        System.out.println(zhangsan );
    }

    @Test
    public void test5() {
        Optional<Employee> zhangsan = Optional.ofNullable(new Employee(1, "zhangsan", 2, 10, Employee.Status.BUSY));

//        Optional<String> s = zhangsan.map((e) -> e.getName());

        Optional<String> s = zhangsan.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(s.get());
    }


    //例题：需求：获取一个男人心中女神的名称
    @Test
    public void test6() {

    }

    public String getGodnessName(Man man) {
        if (man != null) {
            Goddness goddness = man.getGoddness();
            if (goddness != null) {
                return goddness.getName();
            }
        }
        return "cy";
    }

    public String getGodnessName2(Optional<NewMan> newMan) {
        return newMan.orElse(new NewMan())
                .getGoddness()
                .orElse(new Goddness("cy"))
                .getName();
    }

    @Test
    public void test7() {
        Optional<NewMan> op = Optional.ofNullable(new NewMan());

        String godnessName2 = getGodnessName2(op);

        System.out.println(godnessName2);
    }
}
```



```java
package com.lhh.optional;

public class Goddness {

    private String name;

    public Goddness() {
    }

    public Goddness(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Goddness{" +
                "name='" + name + '\'' +
                '}';
    }
}

```

```java
package com.lhh.optional;

public class Man {

    private Goddness goddness;

    public Man() {
    }

    public Man(Goddness goddness) {
        this.goddness = goddness;
    }

    public Goddness getGoddness() {
        return goddness;
    }

    public void setGoddness(Goddness goddness) {
        this.goddness = goddness;
    }

    @Override
    public String toString() {
        return "Man{" +
                "goddness=" + goddness +
                '}';
    }
}

```

```java
package com.lhh.optional;

import java.util.Optional;

public class NewMan {

    private Optional<Goddness> goddness = Optional.empty();

    public NewMan(Optional<Goddness> goddness) {
        this.goddness = goddness;
    }

    public NewMan() {
    }

    public Optional<Goddness> getGoddness() {
        return goddness;
    }

    public void setGoddness(Optional<Goddness> goddness) {
        this.goddness = goddness;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "goddness=" + goddness +
                '}';
    }
}

```

## 七、接口

### 7.1 默认方法

```java
//原本接口中只允许有 全局静态常量和抽象方法
public interface MyFunc {

    //java8中新增了 拥有了可以实现的默认的方法
    default String getName() {
        return "哈哈";
    }

    // 也可以有静态方法
    public static void show() {
        System.out.println("这是一个静态方法");
    }
}
```

类优先原则：

![在这里插入图片描述](https://tva1.sinaimg.cn/large/008i3skNgy1gqo7nceunij30x80g7nao.jpg)

```java
public class MyClass {

    public String getName() {
        return "嘿嘿嘿";
    }
}
```

```java
//原本接口中只允许有 全局静态常量和抽象方法
public interface MyFunc {

    //java8中新增了 拥有了可以实现的默认的方法
    default String getName() {
        return "哈哈";
    }

    // 也可以有静态方法
    public static void show() {
        System.out.println("这是一个静态方法");
    }
}
```

```java
public interface MyInterface {

    default String getName() {
        return "嘻嘻";
    }
}
```

```java
public class SubClass implements MyFunc,MyInterface{
    @Override
    public String getName() {
        return MyInterface.super.getName();
    }
}
```

```java
/**
 *
 * 接口中的默认方法和静态方法
 * */
public class TestDefaultStaticMethod {

    public static void main(String[] args) {
        /**
         * 类优先
         * */
        SubClass sub = new SubClass();
        System.out.println(sub.getName());

        MyFunc.show();
    }
}
```

### 7.2 静态方法

```java
//原本接口中只允许有 全局静态常量和抽象方法
public interface MyFunc {

    //java8中新增了 拥有了可以实现的默认的方法
    default String getName() {
        return "哈哈";
    }

    // 也可以有静态方法
    public static void show() {
        System.out.println("这是一个静态方法");
    }
}
```

## 八. DateTime API

### 8.1 解决传统线程安全问题

```java

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatThreadLocal {

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Date convert(String source) throws ParseException {
        return df.get().parse(source);
    }
}
```

```java
package com.lhh.newtime;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestSimpleDateFormat extends Exception{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//
//        Callable<Date> task = new Callable<Date>() {
//            @Override
//            public Date call() throws Exception {
//                //线程安全方式
//                return DateFormatThreadLocal.convert("20160812");
//                //线程不安全方式
////                return sdf.parse("20160812");
//            }
//        };
//
//        ExecutorService pool = Executors.newFixedThreadPool(10);
//
//        List<Future<Date>> futures = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            futures.add(pool.submit(task));
//        }
//
//
//        for (Future<Date> future : futures) {
//
//            System.out.println(future.get());
//        }
//
//        pool.shutdown();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                //线程安全方式
                return LocalDate.parse("20160812", dtf);
                //线程不安全方式
//                return sdf.parse("20160812");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            futures.add(pool.submit(task));
        }


        for (Future<LocalDate > future : futures) {

            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
```

### 8.2 本地时间、日期

​	![img](https://tva1.sinaimg.cn/large/008i3skNgy1gqozjmeopzj30uq0kbthx.jpg)

常用方法：

| **方法名**                                                   | **返回值类型**       | **解释**                                                     |
| :----------------------------------------------------------- | -------------------- | ------------------------------------------------------------ |
| now( )                                                       | static LocalDateTime | 从默认时区的系统时钟获取当前日期                             |
| of(int year, int month, int dayOfMonth, int hour, int minute, int second) | static LocalDateTime | 从年，月，日，小时，分钟和秒获得 LocalDateTime的实例，将纳秒设置为零 |
| plus(long amountToAdd, TemporalUnit unit)                    | LocalDateTime        | 返回此日期时间的副本，并添加指定的数量                       |
| get(TemporalField field)                                     | int                  | 从此日期时间获取指定字段的值为 int                           |

```java
    // 1.LocalDate  LocalTime  LocalDateTime
    @Test
    public void test1() {
        // 获取当前时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);  //2021-05-18T19:46:00.996

        LocalDateTime of = LocalDateTime.of(2021, 12, 4, 5, 20);
        System.out.println(of);


        //当前年份加上两年
        LocalDateTime localDateTime = ldt.plusYears(2);
        System.out.println(localDateTime);   //2023-05-18T19:46:00.996

        //当前月份减去两个月
        LocalDateTime localDateTime1 = ldt.minusMonths(2);
        System.out.println(localDateTime1);  //2021-03-18T19:47:13.529


        System.out.println("--------------------------");

        System.out.println(ldt.getYear());  //2021
        System.out.println(ldt.getMonthValue());  //5
        System.out.println(ldt.getDayOfMonth());   //18
        System.out.println(ldt.getHour());    //19
        System.out.println(ldt.getMinute());  //48
        System.out.println(ldt.getSecond());  //46
    }
```

### 8.3 时间戳

Instant：以 Unix 元年 1970-01-01 00:00:00 到某个时间之间的毫秒值

```java
    //2.Instant  时间戳（以1970年0点0时0分0秒到某个时间到毫秒值）  计算机所能识别的时间间隔
    @Test
        public void test2() {
            Instant ins1 = Instant.now();   //默认获取以utc时区为基础的
            System.out.println(ins1);

            // 带有偏移时间差的信息
            OffsetDateTime offsetDateTime = ins1.atOffset(ZoneOffset.ofHours(8));
            // 相比较utc加上8个小时
            System.out.println(offsetDateTime);  //2021-05-18T19:52:31.836+08:00


            //转换为毫秒值  ->时间戳
            System.out.println(ins1.toEpochMilli());   //1621338924590


            //依据时间戳显示时间
            Instant instant = Instant.ofEpochMilli(1);
            System.out.println(instant);   //1970-01-01T00:00:00.001Z
    }
```

### 8.4 日期、时间差

- Duration：计算两个时间之间的间隔
- Period：计算两个日期之间的间隔

```java
    //3.Duation  计算两个时间之间的间隔
    //Period：计算两个"日期之间的间隔"
    @Test
    public void test3() {

        // Duation计算两个时间之间的间隔
        Instant ins1 = Instant.now();

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e) {

        }
        Instant ins2 = Instant.now();

        Duration between = Duration.between(ins1, ins2);
        System.out.println(between.toMillis());  //计算两个时间之间的间隔  毫秒 1004

        System.out.println("--------------------------------------");

        LocalDateTime ldt1 = LocalDateTime.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        LocalDateTime ldt2 = LocalDateTime.now();
        System.out.println(Duration.between(ldt1, ldt2).toMillis());  //1001
    }

    @Test
    public void test4() {
        // period计算两个日期之间的间隔
        LocalDate ld1 = LocalDate.of(2021,4,10);
        LocalDate ld2 = LocalDate.now();

        Period between = Period.between(ld1, ld2);
        System.out.println(between);

        System.out.println(between.getYears());  //0
        System.out.println(between.getMonths());  //1
        System.out.println(between.getDays());   //8
    }
```

### 8.5 时间矫正器

![img](https://tva1.sinaimg.cn/large/008i3skNgy1gqozrjuwgdj30v80jljze.jpg)

```java
    //4.TemporaAdjuster; 时间矫正器
    @Test
    public void test5() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);   //2021-05-19T22:53:08.870

        LocalDateTime localDateTime = ldt.withDayOfMonth(10);
        System.out.println(localDateTime);   //2021-05-10T22:53:08.870

        //计算下一个周日
        LocalDateTime with = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(with);   //2021-05-23T22:58:13.293


        //自定义下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dayOfWeek = ldt4.getDayOfWeek();

            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);   //2021-05-20T23:03:01.847
    }
```

### 8.5 时间格式化

```java
    //5.时间格式化和时区的处理
    @Test
    public void test6() {
        //DateTimeFormatter  格式化时间和日期
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;

        LocalDateTime ldt = LocalDateTime.now();

        String format = dtf.format(ldt);

        System.out.println(format);  //2021-05-19T23:06:59.431

        System.out.println("----------------------------------");


        // 时间->str
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

        String format1 = dtf2.format(ldt);

        System.out.println(format1);  //2021年05月19日'

        //str->时间  解析字符串的时间格式
        LocalDate parse = LocalDate.parse(format1, dtf2);
        System.out.println(parse);   //2021-05-19
    }
```

### 8.6 时区处理

```java
    //6.时区的api操作
    @Test
    public void test7() {
        //列出所有的时区
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
//        availableZoneIds.forEach(System.out::println);


        //指定时区显示时间
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Marigot"));

        System.out.println(now);   //2021-05-19T11:30:55.838

        LocalDateTime now1 = LocalDateTime.now();

        // 带有时区的时间和日期
        ZonedDateTime dateTime = now1.atZone(ZoneId.of("America/Marigot"));

        System.out.println(dateTime);   //2021-05-19T23:32:31.169-04:00[America/Marigot]
    }
```

## 九、注释

### 9.1 重复注释

定义注解：

```java
@Repeatable(MyAnnotions.class)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface MyAnnocation {

    String value() default "lhh";
}
```

定义容器：

```java
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface MyAnnotions {

    MyAnnocation[] value();
}
```

测试：

```java
/**
 * 重复注解
 * */
public class TestAnnoation {

    @MyAnnocation("hello")
    @MyAnnocation("world")
    public void show(@MyAnnocation("abc") String str) {

    }
    
    @Test
    public void test1() throws Exception {
        Class<TestAnnoation> classz = TestAnnoation.class;

        Method show = classz.getMethod("show");
        MyAnnocation[] annotationsByType = show.getAnnotationsByType(MyAnnocation.class);
        for (MyAnnocation myannotion:annotationsByType) {
            System.out.println(myannotion.value());
        }
    }
}
```

### 9.2 类型注解

Java 8 新增注解：新增ElementType.TYPE_USE 和ElementType.TYPE_PARAMETER（在Target上）
