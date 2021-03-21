package com.orm.dao;

import com.orm.config.PostgreQueries;
import com.orm.model.User;

import java.sql.SQLException;
import java.util.List;

interface IUserDao {

    //    CRUD Operations

    User create(PostgreQueries postgreQueries) throws SQLException;

    void updateFirstName(PostgreQueries postgreQueries) throws SQLException;

    void updateLastName(PostgreQueries postgreQueries) throws SQLException;

    void updateFirstAndLastName(PostgreQueries postgreQueries) throws SQLException;

    User searchById(PostgreQueries postgreQueries) throws SQLException;

    List<User> searchByFirstName(PostgreQueries postgreQueries) throws SQLException;

    List<User> searchByLastName(PostgreQueries postgreQueries) throws SQLException;

    List<User> searchByFirstAndLastName(PostgreQueries postgreQueries) throws SQLException;

    void deleteById(PostgreQueries postgreQueries) throws SQLException;

}
