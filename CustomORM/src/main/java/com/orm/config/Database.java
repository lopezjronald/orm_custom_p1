package com.orm.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static final String url = "jdbc:postgresql://postgresql-class.custom-orm.cnftml2dw6bt.us-east-2.rds.amazonaws.com";
    public static final String username = "postgres";
    public static final String password = "password";

    public final Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, username, password);
    }


}
