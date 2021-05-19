package com.lhh.annotation;

import org.junit.Test;

import java.lang.reflect.Method;

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
