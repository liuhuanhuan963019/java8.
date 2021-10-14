package com.lhh.apply;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class sortList {

    @Test
    public  void Test1() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(new Integer(5));
        list.add(new Integer(13));
        list.add(new Integer(4));
        list.add(new Integer(9));
        Collections.sort(list);
        System.out.println(list.toString());
    }

    @Test
    public void Test2() {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("lhh",13));
        list.add(new Student("wcy",10));
        list.add(new Student("tw",25));
        Collections.sort(list);
        System.out.println(list.toString());
    }

    @Test
    public void Test3() {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("lhh",13));
        list.add(new Student("wcy",10));
        list.add(new Student("tw",25));
        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int diff = o1.getAge() - o2.getAge();
                if (diff > 0) {
                    return 1;
                }else if (diff < 0) {
                    return -1;
                }
                    return 0; //相等为0
                }
        });
        System.out.println(list.toString());
    }

    class Student implements Comparable<Student> {

        private String name; //姓名

        private int age; // 年龄

        public Student() {
        }

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
        //重写Comparable接口的compareTo方法，
        // 根据年龄升序排列，降序修改相减顺序即可
        @Override
        public int compareTo(Student o) {
            return this.age-o.getAge();
        }
    }
}
