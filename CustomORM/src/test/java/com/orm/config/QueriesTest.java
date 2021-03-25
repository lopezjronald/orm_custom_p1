package com.orm.config;

import com.orm.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class QueriesTest {

    Connection postgresConnection = new PostgreDatabase().getConnection();
    Queries queries = new Queries();

    QueriesTest() throws SQLException {
    }


    @Test
    void searchByIdTest() throws SQLException {
        User searchUser = queries.searchById(1, postgresConnection);
        Assertions.assertEquals(1, searchUser.getId());
        Assertions.assertEquals("ronaldo", searchUser.getFirstName());
        Assertions.assertEquals("lopez", searchUser.getLastName());
    }

    @Test
    void searchByFirstName() {
        List<User> users = queries.searchByFirstName(postgresConnection, "ronaldo");
        Assertions.assertNotNull(users);
        Assertions.assertTrue(users.size() > 0);
    }

    @Test
    void searchByLastNameTest() throws SQLException {
        List<User> users = queries.searchByLastName(postgresConnection, "lopez");
        Assertions.assertNotNull(users);
        Assertions.assertTrue(users.size() > 0);
    }

    @Test
    void searchByFirstAndLastNameTest() throws SQLException {
        List<User> users = queries.searchByFirstAndLastName(postgresConnection, "blue", "sky");
        Assertions.assertNotNull(users);
        Assertions.assertTrue(users.size() > 0);
    }

    @Test
    void deleteById() {
    }

    @Test
    void create() {
    }

    @Test
    void updateFieldInColumn() {
    }

    @Test
    void createTable() {
    }

    @Test
    void dropTable() {
    }

    @Test
    void createColumn() {
    }

    @Test
    void askForValue() {
    }

    @Test
    void askForId() {
    }

    @Test
    void askForTableName() {
    }

    @Test
    void askForColumnName() {
    }

    @Test
    void askForDataType() {
    }

    @Test
    void askForConstraint() {
    }

    @Test
    void askForConstraintAmount() {
    }

    @Test
    void askForConstraintType() {
    }

    @Test
    void askForColumnAmount() {
    }

    @Test
    void showTables() {
    }

    @Test
    void getColumnNames() {
    }

    @Test
    void askForChoice() {
    }
}