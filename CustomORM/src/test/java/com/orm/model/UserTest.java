package com.orm.model;

import com.orm.config.PostgreDatabase;
import com.orm.config.Queries;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Scanner;

class UserTest {

    User user = new User();
    Connection postgresConnection = new PostgreDatabase().getConnection();
    Queries queries = new Queries();
    Scanner scanner = new Scanner(System.in);

    UserTest() throws SQLException {
    }


    @Test
    void searchByIdTest() throws SQLException {
        User searchUser = queries.searchById(1, postgresConnection);
        System.out.println(searchUser.toString());
        Assertions.assertEquals(1, searchUser.getId());
        Assertions.assertEquals("ronald", searchUser.getFirstName());
        Assertions.assertEquals("lopez", searchUser.getLastName());
    }

    @Test
    void createNewUserTest () throws SQLException {
       User newUser = user.create(postgresConnection, "BluE", "SKy");
       Assertions.assertNotNull(newUser);
       Assertions.assertEquals("blue", newUser.getFirstName());
       Assertions.assertEquals("sky", newUser.getLastName());

    }

//    @Test
//    void searchByFirstName() {
//    }
//
//    @Test
//    void searchByLastName() {
//    }
//
//    @Test
//    void searchByFirstAndLastName() {
//
//    }

    @Test
    void getIdFirstNameAndLastNameTest() throws SQLException {
        User user = new User(13, "kristie", "rodriguez");
        Assertions.assertEquals("kristie", user.getFirstName());
        Assertions.assertEquals("rodriguez", user.getLastName());
    }

}