package com.orm.model;

import com.orm.config.PostgreDatabase;
import com.orm.config.Queries;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class UserTest {

    Connection postgresConnection = new PostgreDatabase().getConnection();
    Queries queries = new Queries();

    UserTest() throws SQLException {
    }


    @Test
    void searchByIdTest() throws SQLException {
        User searchUser = queries.searchById(1, postgresConnection);
        Assertions.assertEquals(1, searchUser.getId());
        Assertions.assertEquals("ronald", searchUser.getFirstName());
        Assertions.assertEquals("lopez", searchUser.getLastName());
    }

    @Test
    void createNewUserTest() throws SQLException {
        User newUser = queries.create(postgresConnection, "BluE", "SKy");
        Assertions.assertNotNull(newUser);
        int id = newUser.getId();
        Assertions.assertEquals("blue", newUser.getFirstName());
        Assertions.assertEquals("sky", newUser.getLastName());
        queries.deleteById(postgresConnection, id);
    }

    @Test
    void searchByFirstNameTest() throws SQLException {
        List<User> users = queries.searchByFirstName(postgresConnection, "ronald");
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
    void getIdFirstNameAndLastNameTest() throws SQLException {
        User user = new User(13, "kristie", "rodriguez");
        Assertions.assertNotNull(user);
        Assertions.assertEquals(13, user.getId());
        Assertions.assertEquals("kristie", user.getFirstName());
        Assertions.assertEquals("rodriguez", user.getLastName());
    }

}