package com.lhh.optional;


import com.lhh.streamApi.Employee;
import org.junit.Test;

import java.util.Optional;

public class TestOptional {

    /**
     * optional 容器类的常用方法有：
     * Optional.of(T t): 创建一个optional实例
     * Optional.empty(T t): 创建一个空的optional实例
     * Optional.ofNullable(T t):若t不为null,创建optional实例，否则创建空实例。
     * isPresent()  判断是否有值
     * orElse(T t)  如果调用对象包含值，则返回该值，否则返回T
     * orElseGet(Supplier s) 如果调用对象包含值，则返回该值，否则返回s获取的值
     * map(Function f)   如果有值对其进行处理，并返回处理后的Optional,否则返回Optional.empty()
     * flatMap(Function f) 与mapper类似，要求返回值必须是Optional
     * */

    @Test
    public void test1() {
        // 可以快速定位空指针异常的地方  of方式不能传入null值
        Optional<Employee> employee = Optional.of(new Employee());
        Employee employee1 = employee.get();
        System.out.println(employee1);

    }

    @Test
    public void test2() {
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.get());
    }

    @Test
    public void test3() {
        //两种方式均可，只是传入null时，无法获取到值
//        Optional<Object> empty = Optional.ofNullable(null);
        Optional<Object> empty = Optional.ofNullable(new Employee());
        System.out.println(empty.get());
    }

    @Test
    public void test4() {
        Optional<Employee> empty = Optional.ofNullable(new Employee());

//        if (empty.isPresent()) {
//            System.out.println(empty.get());
//        }
        // 避免了空指针异常的问题
        Employee zhangsan = empty.orElse(new Employee(1, "zhangsan", 2, 10, Employee.Status.BUSY));
        System.out.println(zhangsan );
    }

    @Test
    public void test5() {
        Optional<Employee> zhangsan = Optional.ofNullable(new Employee(1, "zhangsan", 2, 10, Employee.Status.BUSY));

//        Optional<String> s = zhangsan.map((e) -> e.getName());

        Optional<String> s = zhangsan.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(s.get());
    }


    //例题：需求：获取一个男人心中女神的名称
    @Test
    public void test6() {

    }

    public String getGodnessName(Man man) {
        if (man != null) {
            Goddness goddness = man.getGoddness();
            if (goddness != null) {
                return goddness.getName();
            }
        }
        return "cy";
    }

    public String getGodnessName2(Optional<NewMan> newMan) {
        return newMan.orElse(new NewMan())
                .getGoddness()
                .orElse(new Goddness("cy"))
                .getName();
    }

    @Test
    public void test7() {
        Optional<NewMan> op = Optional.ofNullable(new NewMan());

        String godnessName2 = getGodnessName2(op);

        System.out.println(godnessName2);
    }
}
