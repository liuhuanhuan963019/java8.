package com.lhh.lamdba;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/12 3:51
 */
@FunctionalInterface
public interface LamdbaPracticeInterface2<T,R> {

    public R getValue(T t1,T t2);
}
