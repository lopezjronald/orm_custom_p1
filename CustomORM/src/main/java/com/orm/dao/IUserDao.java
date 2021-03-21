package com.orm.dao;

import com.orm.config.PostgreQueries;
import com.orm.model.User;

import java.sql.SQLException;
import java.util.List;

interface IUserDao {

    //    CRUD Operations

    User createNewUser(PostgreQueries postgreQueries) throws SQLException;

    User updateUserFirstName(PostgreQueries postgreQueries) throws SQLException;

    User updateUserLastName(PostgreQueries postgreQueries) throws SQLException;

    User updateUserFirstAndLastName(PostgreQueries postgreQueries) throws SQLException;

    User searchById(PostgreQueries postgreQueries) throws SQLException;

    List<User> searchByFirstName(PostgreQueries postgreQueries) throws SQLException;

    List<User> searchByLastName(PostgreQueries postgreQueries) throws SQLException;

    List<User> searchByFirstAndLastName(PostgreQueries postgreQueries) throws SQLException;

    Boolean deleteUserById(PostgreQueries postgreQueries) throws SQLException;

}
