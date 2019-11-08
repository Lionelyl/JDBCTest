package com.jdbctest.dao;

import com.jdbctest.utity.User;

import java.util.List;

public interface UserDao {

    /**
     *
     * @param user
     * @return int
     */
    public int insertUser(User user);

    /**
     *
     * @param id
     * @return int
     */
    public int deleteUser(int id);

    /**
     *
     * @param user
     * @return int
     */
    public int updateUser(User user);

    /**
     *
     * @param index
     * @param pageSize
     * @return userlist
     */
    public List<User> listUserByPage(int index, int pageSize);

    /**
     *
     * @param username
     * @param pwd
     * @return user
     */
    public User findUserByNamePwd(String username, String pwd);

    /**
     *
     * @param id
     * @return user
     */
    public User findUserById(int id);
}
