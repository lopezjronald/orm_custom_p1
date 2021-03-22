package com.orm.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase {
    public static final String DB_NAME = "testjava.db";
    public static final String URL = "jdbc:sqlite:C:\\Users\\Shadow\\Desktop\\Projects\\custom-java-orm\\CustomORM\\" + DB_NAME;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}


