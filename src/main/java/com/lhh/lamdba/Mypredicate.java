package com.lhh.lamdba;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/11 22:46
 */
@FunctionalInterface
public interface Mypredicate<T> {

    public boolean test(T t);
}
