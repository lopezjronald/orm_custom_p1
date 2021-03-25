package com.orm.model;

import com.orm.config.PostgreDatabase;
import com.orm.dao.DatabaseDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class UserTest {

    Connection postgresConnection = new PostgreDatabase().getConnection();
    DatabaseDaoImpl databaseDaoImpl = new DatabaseDaoImpl();
    User user = new User();

    UserTest() throws SQLException {
    }

    @BeforeEach
    void createUser() throws SQLException {
        int id = 5555;
        String firstName = "blue";
        String lastName = "sky";
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
    }

    @Test
    void getIdTest() throws SQLException {
        Assertions.assertEquals(5555, user.getId());
    }

    @Test
    void setIdTest() throws SQLException {
        int id = 9999;
        user.setId(id);
        Assertions.assertEquals(id, user.getId());
    }

    @Test
    void getFirstNameTest() throws SQLException {
        Assertions.assertNotNull(user.getLastName());
    }

    @Test
    void setFirstNameTest() throws SQLException {
        String firstName = "ronald";
        user.setFirstName(firstName);
        Assertions.assertEquals(firstName, user.getFirstName());
    }

    @Test
    void getLastNameTest() throws SQLException {
        Assertions.assertNotNull(user.getLastName());
    }

    @Test
    void setLastNameTest() throws SQLException {
        String lastName = "Lopez";
        user.setLastName(lastName);
        Assertions.assertEquals(lastName, user.getLastName());
    }
}