package com.orm.dao;

import com.orm.config.Queries;
import com.orm.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements IUserDao {

    Queries queries = new Queries();

    public UserDaoImpl() throws SQLException {
    }

    @Override
    public void createTable(Connection connection) throws SQLException {
        queries.createTable(connection);
    }

    @Override
    synchronized public void dropTable(Connection connection) throws SQLException {
        queries.dropTable(connection);
    }

    @Override
    public void create(Connection connection) throws SQLException {
        queries.create(connection);
    }

    @Override
    synchronized public void updateFirstName(Connection connection) throws SQLException {
        queries.updateFirstName(connection);
    }

    @Override
    synchronized public void updateLastName(Connection connection) throws SQLException {
        queries.updateLastName(connection);
    }

    @Override
    synchronized public void  updateFirstAndLastName(Connection connection) throws SQLException {
        queries.updateFirstAndLastName(connection);
    }

    @Override
    public User searchById(Connection connection) throws SQLException {
        return queries.searchById(connection);
    }

    @Override
    public List<User> searchByFirstName(Connection connection) throws SQLException {
        return queries.searchByFirstName(connection);
    }

    @Override
    public List<User> searchByLastName(Connection connection) throws SQLException {
        return queries.searchByLastName(connection);
    }

    @Override
    public List<User> searchByFirstAndLastName(Connection connection) throws SQLException {
        return queries.searchByFirstAndLastName(connection);
    }

    @Override
    synchronized public void deleteById(Connection connection) throws SQLException {
        queries.deleteById(connection);
    }
}
