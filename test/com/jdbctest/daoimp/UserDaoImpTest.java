package com.jdbctest.daoimp;

import com.jdbctest.utity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserDaoImpTest {

    private UserDaoImp userDaoImp = new UserDaoImp();

    //先在数据库中插入多组用户
    //User(1,"angle","111")，User(2,"bob","222")，User(3,"candy","333")

    @Test
    void testInsertUser() {
        User user = new User(10,"tom","123");
        int num = userDaoImp.insertUser(user);
        System.out.println(num);
        if(num != 0){
            System.out.println("数据插入成功");
        }
        else
            System.out.println("数据插入失败");
    }

    @Test
    void testDeleteUser() {
        int num = userDaoImp.deleteUser(10);
        if(num != 0){
            System.out.println("数据删除成功");
        }
        else
            System.out.println("数据删除失败");

    }


    @Test
    void testFindUserById() {
        User user = userDaoImp.findUserById(1);
        if(user != null){
            System.out.println(user);
        }
    }

    @Test
    void testUpdateUser() {
        User userChange = new User(1,"angle","1111");
        int num = userDaoImp.updateUser(userChange);
        if(num != 0){
            System.out.println("数据更新成功");
        }
        else
            System.out.println("数据更新失败");
    }

    @Test
    void testFindUserByNamePwd() {
        String username = "bob";
        String pwd = "222";
        User user = userDaoImp.findUserByNamePwd(username,pwd);
        if(user != null){
            System.out.println(user);
        }
    }

    @Test
    void tesyListUserByPage() {

        List<User> userList = userDaoImp.listUserByPage(1,3);
        if(!userList.isEmpty()){
            for(User user : userList)
                System.out.println(user);
        }
    }


}