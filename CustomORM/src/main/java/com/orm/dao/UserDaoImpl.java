package com.orm.dao;

import com.orm.config.PostgreQueries;
import com.orm.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements IUserDao {


    @Override
    public User createNewUser(PostgreQueries postgreQueries) throws SQLException {
        return null;
    }

    @Override
    public User updateUserFirstName(PostgreQueries postgreQueries) throws SQLException {
        return null;
    }

    @Override
    public User updateUserLastName(PostgreQueries postgreQueries) throws SQLException{
        return null;
    }

    @Override
    public User updateUserFirstAndLastName(PostgreQueries postgreQueries) throws SQLException {
        return null;
    }

    @Override
    public User searchById(PostgreQueries postgreQueries) throws SQLException {
        return postgreQueries.searchUserById();
    }

    @Override
    public List<User> searchByFirstName(PostgreQueries postgreQueries) throws SQLException {
        return postgreQueries.searchByFirstName();
    }

    @Override
    public List<User> searchByLastName(PostgreQueries postgreQueries) throws SQLException {
        return postgreQueries.searchByLastName();
    }

    @Override
    public List<User> searchByFirstAndLastName(PostgreQueries postgreQueries) throws SQLException {
        return postgreQueries.searchByFirstAndLastName();
    }

    @Override
    public Boolean deleteUserById(PostgreQueries postgreQueries) {
        return null;
    }
}
