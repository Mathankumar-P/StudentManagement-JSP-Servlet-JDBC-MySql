package com.jdbcutil;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {

    private static HikariDataSource dataSource = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getJdbcConnection() {
        String path = "E:\\StudentManagement-JSP-Servlet-JDBC-MySql\\src\\main\\java\\com\\properties\\db.properties";
        
        if (dataSource == null) {
            HikariConfig config = new HikariConfig(path);
            dataSource = new HikariDataSource(config);
        }
        
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}


