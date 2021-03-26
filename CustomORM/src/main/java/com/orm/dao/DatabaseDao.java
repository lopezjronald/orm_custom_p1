package com.orm.dao;

import com.orm.model.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;


public interface DatabaseDao {

    User getById(int id, Connection connection);

    ArrayList<User> getByFirstName(Connection connection, String firstName);

    ArrayList<User> getByLastName(Connection connection, String lastName);

    ArrayList<User> getByFirstAndLastName(Connection connection, String firstName, String lastName);

    HashMap<String, String> getColumnNames(Connection connection, String tableName);

    ArrayList<String> getTables(Connection connection);

    ArrayList<User> getAllUsers(Connection connection, String tableName);

    String createTable(Connection connection, String tableName);

    String createColumn(Connection connection, String tableName, String columnName, String dataType);

    User createUser(Connection connection, String firstName, String lastName);

    String updateFieldInColumn(Connection connection, String tableName, String columnName, int id, String value);

    String dropTable(Connection connection, String tableName);

    String dropColumn(Connection connection, String tableName, String columnName);

    String askForValue();

    String askForTableName();

    String askForDataType();

    String askForName(String nameType);

    String askForColumnName();

    int deleteById(Connection connection, int id);

    int askForId();

    int askForChoice();

    int askForColumnAmount();


}
