package com.lhh.default_static_met;

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
