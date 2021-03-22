package com.orm.dao;

import com.orm.config.Queries;
import com.orm.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserDaoImpl implements IUserDao {

    Queries queries = new Queries();
    public static final Scanner scanner = new Scanner(System.in);

    public UserDaoImpl() throws SQLException {
    }

    @Override
    synchronized public void createTable(Connection connection) throws SQLException {
        queries.createTable(connection);
    }

    @Override
    synchronized public User create(Connection connection) throws SQLException {
        return queries.create(connection, askForName("first"), askForName("last"));
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
    synchronized public User  updateFirstAndLastName(Connection connection) throws SQLException {
        return queries.updateFirstAndLastName(connection, askForId(), askForName("first"), askForName("last"));
    }

    @Override
    public User searchById(Connection connection) throws SQLException {
        return queries.searchById(askForId(), connection);
    }

    @Override
    public List<User> searchByFirstName(Connection connection) throws SQLException {
        return queries.searchByFirstName(connection, askForName("first"));
    }

    @Override
    public List<User> searchByLastName(Connection connection) throws SQLException {
        return queries.searchByLastName(connection, askForName("last"));
    }

    @Override
    public List<User> searchByFirstAndLastName(Connection connection) throws SQLException {
        return queries.searchByFirstAndLastName(connection, askForName("first"), askForName("last"));
    }

    @Override
    synchronized public void deleteById(Connection connection) throws SQLException {
        queries.deleteById(connection, askForId());
    }

    @Override
    synchronized public void dropTable(Connection connection) throws SQLException {
        queries.dropTable(connection);
    }

    private String askForName(String nameType) {
        while (true) {
            System.out.print("Enter " + nameType + " name: ");
            String name = scanner.nextLine().trim();
            if (!name.equalsIgnoreCase(""))
                return name;
            else
                System.out.print("No entry. Please enter a " + nameType + " name");
        }
    }

    private Integer askForId(){
        System.out.print("Enter ID: ");
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (InputMismatchException e){
                System.out.print("Invalid entry. Please enter a valid ID #: ");
            } catch (Exception e) {
                System.out.print("Something wrong occurred in the system. Please try again.");
            }
        }
    }
}
