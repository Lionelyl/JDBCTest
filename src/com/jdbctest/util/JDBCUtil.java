package com.jdbctest.util;

import java.sql.*;

public class JDBCUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/timeline?characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(ResultSet rs){
        if(rs != null)
            try {
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
    }

    public static void close(PreparedStatement pstmt){
        if(pstmt != null)
        try {
            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void close(Connection conn){
        if(conn != null)
        try {
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

 /*   public static void main(String[] args) {
        Connection conn = JDBCUtil.getConnnection();
    }*/
/*
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }*/
}