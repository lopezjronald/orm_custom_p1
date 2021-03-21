package com.orm.dao;

import com.orm.config.Queries;
import com.orm.model.User;

import java.sql.SQLException;
import java.util.List;

interface IUserDao {

    //    CRUD Operations

    void createTable(Queries queries) throws SQLException;

    void dropTable(Queries queries) throws SQLException;

    void create(Queries queries) throws SQLException;

    void updateFirstName(Queries queries) throws SQLException;

    void updateLastName(Queries queries) throws SQLException;

    void updateFirstAndLastName(Queries queries) throws SQLException;

    User searchById(Queries queries) throws SQLException;

    List<User> searchByFirstName(Queries queries) throws SQLException;

    List<User> searchByLastName(Queries queries) throws SQLException;

    List<User> searchByFirstAndLastName(Queries queries) throws SQLException;

    void deleteById(Queries queries) throws SQLException;

}
