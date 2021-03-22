package com.orm.dao;

import com.orm.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

interface IUserDao {

    //    CRUD Operations

    void createTable(Connection connection) throws SQLException;

    void dropTable(Connection connection) throws SQLException;

    User create(Connection connection, String firstName, String lastName) throws SQLException;

    void updateFirstName(Connection connection) throws SQLException;

    void updateLastName(Connection connection) throws SQLException;

    void updateFirstAndLastName(Connection connection) throws SQLException;

    User searchById(Connection connection) throws SQLException;

    List<User> searchByFirstName(Connection connection) throws SQLException;

    List<User> searchByLastName(Connection connection) throws SQLException;

    List<User> searchByFirstAndLastName(Connection connection) throws SQLException;

    void deleteById(Connection connection) throws SQLException;

}
