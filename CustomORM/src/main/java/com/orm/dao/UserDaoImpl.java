package com.orm.dao;

import com.orm.config.Queries;
import com.orm.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements IUserDao {

    @Override
    public void createTable(Queries queries) throws SQLException {
        queries.createTable();
    }

    @Override
    public void dropTable(Queries queries) throws SQLException {
        queries.dropTable();
    }

    @Override
    public void create(Queries queries) throws SQLException {
        queries.create();
    }

    @Override
    public void updateFirstName(Queries queries) throws SQLException {
        queries.updateFirstName();
    }

    @Override
    public void updateLastName(Queries queries) throws SQLException {
        queries.updateLastName();
    }

    @Override
    public void updateFirstAndLastName(Queries queries) throws SQLException {
        queries.updateFirstAndLastName();
    }

    @Override
    public User searchById(Queries queries) throws SQLException {
        return queries.searchById();
    }

    @Override
    public List<User> searchByFirstName(Queries queries) throws SQLException {
        return queries.searchByFirstName();
    }

    @Override
    public List<User> searchByLastName(Queries queries) throws SQLException {
        return queries.searchByLastName();
    }

    @Override
    public List<User> searchByFirstAndLastName(Queries queries) throws SQLException {
        return queries.searchByFirstAndLastName();
    }

    @Override
    public void deleteById(Queries queries) throws SQLException {
        queries.deleteById();
    }
}
