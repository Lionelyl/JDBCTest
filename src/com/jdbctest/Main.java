package com.jdbctest;

import com.jdbctest.daoimp.UserDaoImp;
import com.jdbctest.utity.User;

public class Main {
    public static void main(String[] args) {
        User user = new User(1,"tom","123");
        UserDaoImp udl = new UserDaoImp();

        //测试插入
        int num = udl.insertUser(user);
        if(num != 0)
            System.out.println("数据插入成功");
        else
            System.out.println("数据插入失败");

        //测试查询
        System.out.println(udl.findUserById(1).toString());

        //测试更新
        User user1 = new User(1,"tom","123456");
        udl.updateUser(user1);
        System.out.println(udl.findUserById(1).toString());

        //测试删除
        num = udl.deleteUser(1);
        if(num != 0)
            System.out.println("数据删除成功");
        else
            System.out.println("数据删除失败");

    }
}
