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
    public void updateFirstName(PostgreQueries postgreQueries) throws SQLException {
        postgreQueries.updateFirstName();
    }

    @Override
    public void updateLastName(PostgreQueries postgreQueries) throws SQLException{
        postgreQueries.updateLastName();
    }

    @Override
    public void updateFirstAndLastName(PostgreQueries postgreQueries) throws SQLException {
        postgreQueries.updateFirstAndLastName();
    }

    @Override
    public User searchById(PostgreQueries postgreQueries) throws SQLException {
        return postgreQueries.searchById();
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
