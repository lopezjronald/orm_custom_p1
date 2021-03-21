package com.orm.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreDatabase {

    public static final String URL = "jdbc:postgresql://custom-orm.cnftml2dw6bt.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=public";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "password";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

