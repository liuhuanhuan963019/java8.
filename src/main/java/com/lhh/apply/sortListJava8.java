package com.lhh.apply;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘欢欢
 * @version 1.0
 * @date 2021/10/14 5:14 下午
 */
public class sortListJava8 {


    @Test
    public void lambdaTest1(){
        List<User> list = new ArrayList<>();
        list.add(new User(1,"张三"));
        list.add(new User(4,"赵六"));
        list.add(new User(2,"李四"));
        list.add(new User(3,"王五"));

        //条件删除
        list.removeIf(user -> user.getUserId() == 3);
        System.out.println("--------------排序前");
        list.forEach(user -> {
            System.out.println(user);
        });
        list.sort((user1,user2)->user1.getUserId() - user2.getUserId());
        System.out.println("_-------------------正序排序后");
        list.forEach(user -> {
            System.out.println(user);
        });

        System.out.println("--------------------倒序排序后：");
        list.sort((((o1, o2) -> o2.getUserId() - o1.getUserId())));
        list.forEach(user -> {
            System.out.println(user);
        });
    }



    class User{
        private Integer userId;
        private String userName;



        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public User(Integer userId, String userName) {
            this.userId = userId;
            this.userName = userName;
        }

        public User() {
        }

        @Override
        public String toString() {
            return "User{" +
                    "userId=" + userId +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }

}
