package com.lhh.default_static_met;

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
