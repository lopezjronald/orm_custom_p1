package com.orm.dao;

import com.orm.config.Queries;
import com.orm.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserDaoImpl implements IUserDao {

    Queries queries = new Queries();
    public static final Scanner scanner = new Scanner(System.in);

    public UserDaoImpl() throws SQLException {
    }

    @Override
    public void createTable(Connection connection) throws SQLException {
        queries.createTable(connection);
    }

    @Override
    public User create(Connection connection, String firstName, String lastName) throws SQLException {
        return queries.create(connection, firstName, lastName);
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
        int id = -1;
        System.out.print("Enter ID: ");
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (Exception e){
                System.out.println("Invalid entry. Please enter a valid ID #: ");
            }
        }
        return queries.searchById(id, connection);
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

    @Override
    synchronized public void dropTable(Connection connection) throws SQLException {
        queries.dropTable(connection);
    }

}
