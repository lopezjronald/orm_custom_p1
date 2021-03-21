package com.orm.dao;

import com.orm.config.PostgreQueries;
import com.orm.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements IUserDao {


    @Override
    public User create(PostgreQueries postgreQueries) throws SQLException {
        return null;
    }

    @Override
    public User updateFirstName(PostgreQueries postgreQueries) throws SQLException {
        return null;
    }

    @Override
    public User updateLast(PostgreQueries postgreQueries) throws SQLException{
        return null;
    }

    @Override
    public User updateFirstAndLastName(PostgreQueries postgreQueries) throws SQLException {
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
    public void deleteById(PostgreQueries postgreQueries) throws SQLException {
        postgreQueries.deleteById();
    }
}
