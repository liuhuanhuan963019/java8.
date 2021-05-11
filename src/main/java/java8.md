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