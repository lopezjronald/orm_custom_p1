package com.orm.config;

import com.orm.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Queries {

    public final static Scanner scanner = new Scanner(System.in);
    public static Integer id;

    public Queries() throws SQLException {
    }

    /******************** COLUMN TABLE *******************/
    public static final String TABLE_NAME = "users";

    /********************* COLUMN NAMES ****************/
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";

    /******************** CREATE TABLE QUERY ****************/
    public static final String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " ( " + COLUMN_ID + " SERIAL PRIMARY KEY, " +
            COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
            COLUMN_LAST_NAME + " TEXT NOT NULL)";

    /******************** DROP TABLE QUERY ****************/
    public static final String QUERY_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    /****************** SEARCH QUERIES *******************/
    public static final String QUERY_SEARCH_ID = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_ID + " = ? ORDER BY " + COLUMN_LAST_NAME;
    public static final String QUERY_SEARCH_FIRST_NAME = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_FIRST_NAME + " = ? ORDER BY " + COLUMN_LAST_NAME;
    public static final String QUERY_SEARCH_LAST_NAME = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_LAST_NAME + " = ? ORDER BY " + COLUMN_LAST_NAME;
    public static final String QUERY_SEARCH_FIRST_AND_LAST_NAME = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_FIRST_NAME + " = ? AND " +
            COLUMN_LAST_NAME + " = ? ORDER BY " + COLUMN_LAST_NAME;

    /****************** DELETE QUERY *******************/
    public static final String QUERY_DELETE_BY_ID = "DELETE FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_ID + " = ?";

    /****************** CREATE QUERY *******************/
    public static final String QUERY_CREATE = "INSERT INTO " +
            TABLE_NAME + " (" +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + ") VALUES (?, ?)";

    /****************** UPDATE QUERIES *******************/
    public static final String QUERY_UPDATE_FIRST_NAME = "UPDATE " +
            TABLE_NAME + " SET " +
            COLUMN_FIRST_NAME + " = ? WHERE id = ?";

    public static final String QUERY_UPDATE_FIRST_AND_LAST_NAME = "UPDATE " +
            TABLE_NAME + " SET " +
            COLUMN_FIRST_NAME + " = ?, " + COLUMN_LAST_NAME + " = ? WHERE id = ?";

    public static final String QUERY_UPDATE_LAST_NAME = "UPDATE " +
            TABLE_NAME + " SET " +
            COLUMN_LAST_NAME + " = ? WHERE id = ?";

    /***************** SELECT QUERIES ****************/
    public User searchById(int id, Connection connection) throws SQLException {

        User user = new User();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet != null) {
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
            }
            resultSet.close();
        }
        preparedStatement.close();
        return user;
    }

    public ArrayList<User> searchByFirstName(Connection connection) throws SQLException {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_FIRST_NAME);
        preparedStatement.setString(1, firstName.toLowerCase().trim());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet != null) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                users.add(user);
            }
            resultSet.close();
        }
        preparedStatement.close();
        return users;
    }

    public List<User> searchByLastName(Connection connection) throws SQLException {
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_LAST_NAME);
        preparedStatement.setString(1, lastName.toLowerCase().trim());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet != null) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                users.add(user);
            }
            resultSet.close();
        }
        preparedStatement.close();
        return users;
    }

    public List<User> searchByFirstAndLastName(Connection connection) throws SQLException {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_FIRST_AND_LAST_NAME);
        preparedStatement.setString(1, firstName.toLowerCase().trim());
        preparedStatement.setString(2, lastName.toLowerCase().trim());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet != null) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                users.add(user);
            }
            resultSet.close();
        }
        preparedStatement.close();
        return users;
    }

    /**************** DELETE QUERY **********************/
    public void deleteById(Connection connection) throws SQLException{
        connection.setAutoCommit(false);
        System.out.print("Enter ID: ");
        id = 0;
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (Exception e){
                System.out.println("Invalid entry. Please enter a valid ID #: ");
            }
        }
       PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE_BY_ID);
        int result = -1;
        preparedStatement.setInt(1, id);
        try {
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result != 0) {
            connection.commit();
            System.out.println("Deletion of ID: " + id + " was successful.");
            preparedStatement.close();
        } else {
            connection.rollback();
            System.out.println("ID #: " + id + " does not exist or is no longer available.");
        }
        connection.setAutoCommit(true);
    }

    /**************** CREATE QUERY **********************/
    public User create(Connection connection, String firstName, String lastName) throws SQLException{
        while (true) {
           firstName = firstName.trim();
           if (firstName != "" || firstName != null) {
                break;
            } else {
                System.out.println("Invalid entry. Please enter a first name: ");
           }
        }

        while (true) {
            lastName = lastName.trim();
            if (lastName != "" || lastName != null) {
                break;
            } else {
                System.out.println("Invalid entry. Please enter a first name: ");
            }
        }
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE, Statement.RETURN_GENERATED_KEYS);
        int result = -1;
        preparedStatement.setString(1, firstName.trim().toLowerCase());
        preparedStatement.setString(2, lastName.trim().toLowerCase());
        try {
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        User user = new User();
        if (resultSet.next()) {
            user = searchById(resultSet.getInt(1), connection);
        }

        if (result != 0) {
            System.out.println("Entry was successful");
            preparedStatement.close();
        } else {
            System.out.println("ID #: " + id + " does not exist or is no longer available.");
        }
        return user;
    }

    /********************** UPDATE QUERIES *****************/
    public void updateFirstName(Connection connection) throws SQLException {
        System.out.println("Enter ID: ");
        id = 0;
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (Exception e){
                System.out.println("Invalid entry. Please enter a valid ID #: ");
            }
        }
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_FIRST_NAME);
        int result = -1;
        preparedStatement.setString(1, firstName.toLowerCase().trim());
        preparedStatement.setInt(2, id);
        try {
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != 0) {
            System.out.println("Updated ID#: " + id + " was successful.");
            preparedStatement.close();
        } else {
            System.out.println("ID#: " + id + " does not exist or is no longer available. Unable to update");
        }
    }

    public void updateLastName(Connection connection) throws SQLException {
        System.out.println("Enter ID: ");
        id = 0;
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (Exception e){
                System.out.println("Invalid entry. Please enter a valid ID #: ");
            }
        }
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_LAST_NAME);
        int result = -1;
        preparedStatement.setString(1, lastName.toLowerCase().trim());
        preparedStatement.setInt(2, id);
        try {
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != 0) {
            System.out.println("Updated ID#: " + id + " was successful.");
            preparedStatement.close();
        } else {
            System.out.println("ID#: " + id + " does not exist or is no longer available. Unable to update");
        }
    }

    public void updateFirstAndLastName(Connection connection) throws SQLException {
        System.out.println("Enter ID: ");
        id = 0;
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (Exception e){
                System.out.println("Invalid entry. Please enter a valid ID #: ");
            }
        }
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_FIRST_AND_LAST_NAME);
        int result = -1;
        preparedStatement.setString(1, firstName.toLowerCase().trim());
        preparedStatement.setString(2, lastName.toLowerCase().trim());
        preparedStatement.setInt(3, id);
        try {
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != 0) {
            System.out.println("Updated ID#: " + id + " was successful.");
            preparedStatement.close();
        } else {
            System.out.println("ID#: " + id + " does not exist or is no longer available. Unable to update");
        }
    }

    /******************** CREATE TABLE QUERY ************************/
    public void createTable(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE_TABLE);
        preparedStatement.executeUpdate();
        System.out.println("Table created");
        preparedStatement.close();
    }

    /******************** DROP TABLE QUERY ************************/
    public void dropTable(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DROP_TABLE);
        preparedStatement.executeUpdate();
        System.out.println("Table dropped.");
        preparedStatement.close();
    }

}
