package com.orm.config;

import com.orm.model.User;
import com.orm.dao.DatabaseDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class DatabaseDaoImplTest {

    Connection postgresConnection = new PostgreDatabase().getConnection();
    DatabaseDaoImpl databaseDaoImpl = new DatabaseDaoImpl();
    User user;
    List<User> users;

    DatabaseDaoImplTest() throws SQLException {
    }

    @Test
    void searchByIdTest() throws SQLException {
        user = databaseDaoImpl.getById(1, postgresConnection);
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("ronaldo", user.getFirstName());
        Assertions.assertEquals("lopez", user.getLastName());
    }

    @Test
    void searchByFirstName() {
        users = databaseDaoImpl.getByFirstName(postgresConnection, "ronaldo");
        Assertions.assertNotNull(users);
        Assertions.assertTrue(users.size() > 0);
    }

    @Test
    void searchByLastNameTest() throws SQLException {
        users = databaseDaoImpl.getByLastName(postgresConnection, "lopez");
        Assertions.assertNotNull(users);
        Assertions.assertTrue(users.size() > 0);
    }

    @Test
    void searchByFirstAndLastNameTest() throws SQLException {
        users = databaseDaoImpl.getByFirstAndLastName(postgresConnection, "blue", "sky");
        Assertions.assertNotNull(users);
        Assertions.assertTrue(users.size() > 0);
    }

    @Test
    void deleteByIdTest() throws SQLException {
        user = databaseDaoImpl.create(postgresConnection, "Bob", "Jones");
        int idNumber = user.getId();
        int deleteId = databaseDaoImpl.deleteById(postgresConnection, idNumber);
        Assertions.assertNotEquals(-1, deleteId);
    }

    @Test
    void createTest() throws SQLException {
        String firstName = "bob";
        String lastName = "jones";
        user = databaseDaoImpl.create(postgresConnection, firstName, lastName);
        Assertions.assertNotNull(user);
        databaseDaoImpl.deleteById(postgresConnection, user.getId());
    }

    @Test
    void updateFieldInColumnTest() throws SQLException {
        user = databaseDaoImpl.create(postgresConnection, "bob", "jones");
        String tableName = "users";
        String columnName = "first_name";
        int id = user.getId();
        String value = "robert";
        String result = databaseDaoImpl.updateFieldInColumn(postgresConnection, tableName, columnName, id, value);
        Assertions.assertEquals("Update was successful.", result);
        user = databaseDaoImpl.getById(user.getId(), postgresConnection);
        Assertions.assertEquals(value, user.getFirstName());
        databaseDaoImpl.deleteById(postgresConnection, user.getId());
    }

    @Test
    void createTableAndDropTableTest() throws SQLException {
        String tableName = "new_table";
        String createTableResult = databaseDaoImpl.createTable(postgresConnection, tableName);
        Assertions.assertEquals(tableName + " successfully created.", createTableResult);
        String dropTableResult = databaseDaoImpl.dropTable(postgresConnection, tableName);
        Assertions.assertEquals("Table " + tableName + " successfully dropped.", dropTableResult);
    }

    @Test
    void createAndDeleteColumnTest() {
        String tableName = "users";
        String columnName = "email";
        String dataType = "TEXT";
        String constraint = "";
        String createColumn = databaseDaoImpl.createColumn(postgresConnection, tableName, columnName, dataType, constraint);
        Assertions.assertEquals(columnName + " successfully created.", createColumn);
        String deleteColumn = databaseDaoImpl.dropColumn(postgresConnection, tableName, columnName);
        Assertions.assertEquals(columnName + " successfully dropped.", deleteColumn);
    }

    @Test
    void showTables() {
        ArrayList<String> tables = databaseDaoImpl.getTables(postgresConnection);
        Assertions.assertNotNull(tables);
    }

    @Test
    void getColumnNames() {
        String tableName = "users";
        HashMap<String, String> columns = databaseDaoImpl.getColumnNames(postgresConnection, tableName);
        Assertions.assertNotNull(columns);
        tableName = "dslfdkfjd";
        HashMap<String, String> nullColumns = databaseDaoImpl.getColumnNames(postgresConnection, tableName);
        Assertions.assertNull(nullColumns);
    }

}