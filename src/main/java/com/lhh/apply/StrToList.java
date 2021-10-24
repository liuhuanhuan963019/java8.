package com.lhh.apply;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StrToList {

    public static void main(String[] args) {
        String str= "1,2,3,4,56";
        // string 转list
        List<String> list  = Arrays.asList(str.split(","));
        System.out.println(list.toString());
        //print:[1, 2, 3, 4, 56]
        list.stream().forEach(System.out::println);

        //list转为string
        String collect = list.stream().collect(Collectors.joining(","));
        System.out.println(collect);
        // print:1,2,3,4,56
    }
}
