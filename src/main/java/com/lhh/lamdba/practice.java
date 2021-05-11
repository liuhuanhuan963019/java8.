package com.lhh.lamdba;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/12 0:47
 */
/**
 * 1.调用Collection.sort()方法，通过定制排序比较两个Employee（先按年龄比，年龄相同按照姓名比）使用Lamdba作为参数传递
 * 2.(1)声明函数式接口，接口中声明抽象方法，String getValue(String str);
 *   (2)声明类 TestLambda，类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值；
 *   (3)再将一个字符串的第二个和第四个索引位置进行截取字串
 * 3.(1)声明一个带两个泛型的函数式接口，泛型类型为<T, R> T 为参数，R 为返回值；
 *   (2)接口中声明对应的抽象方法；
 *   (3)在 TestLambda 类中声明方法，使用接口作为参数，计算两个 Long 类型参数的和；
 *   (4)在计算两个 Long 类型参数的乘积
 * */
public class practice {

}
