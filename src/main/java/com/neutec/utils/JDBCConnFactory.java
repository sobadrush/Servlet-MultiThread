package com.neutec.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnFactory {

    private static JDBCConnFactory FACTORY = new JDBCConnFactory();

    private Connection conn;

    private JDBCConnFactory() {
        try {
            Class.forName("org.sqlite.JDBC");
            // String connStr = "jdbc:sqlite:" + System.getProperty("user.dir") + "/EmpDeptSqliteDB.db";
            String connStr = "jdbc:sqlite:" + "E:/workspace_intellj/Servlet-MultiThread" + "/EmpDeptSqliteDB.db";
            this.conn = DriverManager.getConnection(connStr);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static JDBCConnFactory getInstance() {
        System.out.println(">>> call JDBCConnFactory getInstance() ");
        return FACTORY;
    }

    public Connection getConnection() {
        return this.conn;
    }
}
