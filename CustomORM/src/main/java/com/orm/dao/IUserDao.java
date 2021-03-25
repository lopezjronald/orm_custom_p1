package com.orm.dao;

import com.orm.model.User;

import java.sql.Connection;
import java.sql.SQLException;

interface IUserDao {

    //    CRUD Operations

    void createTable(Connection connection) throws SQLException;

    void dropTable(Connection connection) throws SQLException;

    User create(Connection connection) throws SQLException;

    void updateFieldInColumn(Connection connection) throws SQLException;

    User searchById(Connection connection) throws SQLException;

    void deleteById(Connection connection) throws SQLException;

}
