package com.jdbctest.daoimp;

import com.jdbctest.dao.UserDao;
import com.jdbctest.util.JDBCUtil;
import com.jdbctest.utity.User;
import jdk.nashorn.internal.scripts.JD;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao {

    @Override
    public int insertUser(User user) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            String sql = "insert into user values(?,?,?)";
            //建立连接
            conn = JDBCUtil.getConnnection();
            pstmt = conn.prepareStatement(sql);

            //利用set方法给占位符赋值
            pstmt.setInt(1,user.getId());
            pstmt.setString(2,user.getUsername());
            pstmt.setString(3,user.getPassword());

            //执行sql
            int num = pstmt.executeUpdate();
            return num;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(pstmt);
            JDBCUtil.close(conn);
        }

        return 0;
    }

    @Override
    public int deleteUser(int id) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            String sql = "delete from user where id=?";
            conn = JDBCUtil.getConnnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);

            int num = pstmt.executeUpdate();
            return num;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(pstmt);
            JDBCUtil.close(conn);
        }

        return 0;
    }

    @Override
    public int updateUser(User user) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            String sql = "update user set username=?,password=? where id=?";
            conn = JDBCUtil.getConnnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            pstmt.setInt(3,user.getId());

            int num = pstmt.executeUpdate();
            return num;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(pstmt);
            JDBCUtil.close(conn);
        }
        return 0;
    }


    @Override
    public User findUserById(int id) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            String sql = "select * from user where id=?";
            conn = JDBCUtil.getConnnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"));

            }
            return user;

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(pstmt);
            JDBCUtil.close(conn);
        }

        return null;
    }

    @Override
    public User findUserByNamePwd(String username, String pwd) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            String sql = "select * from user where username=? and password=?";
            conn = JDBCUtil.getConnnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,pwd);
            rs = pstmt.executeQuery();
            while(rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3));
            }
            return user;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(pstmt);
            JDBCUtil.close(conn);
        }

        return null;
    }

    @Override
    public List<User> listUserByPage(int index, int pageSize) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        //用于储存user的list
        List<User> userList = new ArrayList<User>();

        try {
            String sql = "select * from user limit ?,?";
            //建立连接
            conn = JDBCUtil.getConnnection();
            pstmt = conn.prepareStatement(sql);
            //给占位符赋值
            pstmt.setInt(1,pageSize*(index-1));
            pstmt.setInt(2,pageSize);
            //执行sql
            rs = pstmt.executeQuery();
            //遍历rs，取出数据生成user对象，并依次加入userList
            while(rs.next()){
                user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs);
            JDBCUtil.close(pstmt);
            JDBCUtil.close(conn);
        }
        return null;
    }
}
