package com.eseict.gondo;
import java.sql.Statement;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JDBCUtil {
    // 1. 연결
    public static Connection getConnection() {
        Connection conn = null;
        Context initContext;

        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env/pageSize");
            envContext = (Context) initContext.lookup("java:/comp/env/startNo");
            log.info("JDBCUtil-Connection : initContext {} ", envContext);
            DataSource ds = (DataSource) envContext.lookup("jdbc/mariaDB");
            conn = ds.getConnection();
            log.info("JDBCUtil-Connection : 연결성공 ");
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 2. 닫기
    public static void close(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. 취소
    public static void rollback(Connection conn) {
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}