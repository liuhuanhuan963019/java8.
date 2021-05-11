package com.lhh.lamdba;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/11 23:07
 */
public class FilterEmployeeBySalary implements Mypredicate<Employee>{

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 12000;
    }
}
