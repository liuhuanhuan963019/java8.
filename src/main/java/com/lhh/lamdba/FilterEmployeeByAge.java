package com.lhh.lamdba;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/11 22:47
 */
public class FilterEmployeeByAge implements Mypredicate<Employee>{

    @Override
    public boolean test(Employee e) {
        return e.getAge() > 22;
    }
}
