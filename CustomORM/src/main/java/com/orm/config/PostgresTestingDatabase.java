package com.orm.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresTestingDatabase {
    public static final String URL = "jdbc:postgresql://localhost:5432/custom-orm-test";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "password";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Unable to connect to the SQLite database");
            return null;
        }
    }

}


