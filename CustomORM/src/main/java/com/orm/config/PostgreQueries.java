package com.orm.config;

import com.orm.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostgreQueries {
    ////// COLUMN TABLE /////
    public static final String TABLE_NAME = "users";

    /////// COLUMN NAMES //////////////
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";

    /****************** SEARCH QUERIES *******************/
    public static final String QUERY_SEARCH_ID = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_ID + " = ?";
    public static final String QUERY_SEARCH_FIRST_NAME = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_FIRST_NAME + " = ?";
    public static final String QUERY_SEARCH_LAST_NAME = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_LAST_NAME + " = ?";
    public static final String QUERY_SEARCH_FIRST_AND_LAST_NAME = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_FIRST_NAME + " = ? AND " +
            COLUMN_LAST_NAME + " = ?";

    /****************** DELETE QUERY *******************/
    public static final String QUERY_DELETE_BY_ID = "DELETE FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_ID + " = ?";

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
    /*****************************************************************/

    PostgreDatabase postgreDatabase = new PostgreDatabase();
    Connection connection = postgreDatabase.getConnection();
    public final static Scanner scanner = new Scanner(System.in);
    public static Integer id;

    public PostgreQueries() throws SQLException {
    }


    /***************** SELECT QUERIES ****************/

    public User searchById() throws SQLException {
        System.out.print("Enter ID: ");
        int id = 0;
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e){
                System.out.println("Invalid entry. Please enter a valid ID #: ");
            }
        }
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
        }
        return user;
    }

    public ArrayList<User> searchByFirstName() throws SQLException {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_FIRST_NAME);
        preparedStatement.setString(1, firstName.toLowerCase());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet != null) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                users.add(user);
            }
        }
        return users;
    }

    public List<User> searchByLastName() throws SQLException {
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_LAST_NAME);
        preparedStatement.setString(1, lastName.toLowerCase());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet != null) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                users.add(user);
            }
        }
        return users;
    }

    public List<User> searchByFirstAndLastName() throws SQLException {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_FIRST_AND_LAST_NAME);
        preparedStatement.setString(1, firstName.toLowerCase());
        preparedStatement.setString(2, lastName.toLowerCase());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet != null) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                users.add(user);
            }
        }
        return users;
    }

    /**************** DELETE QUERY **********************/

    public void deleteById() throws SQLException{
        System.out.print("Enter ID: ");
        id = 0;
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine());
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
            System.out.println("Deletion of ID: " + id + " was successful.");
        } else {
            System.out.println("ID #: " + id + " does not exist or is no longer available.");
        }
    }

    /********************** UPDATE QUERIES *****************/
    public void updateFirstName() throws SQLException {
        System.out.println("Enter ID: ");
        id = 0;
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e){
                System.out.println("Invalid entry. Please enter a valid ID #: ");
            }
        }
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_FIRST_NAME);
        int result = -1;
        preparedStatement.setString(1, firstName.toLowerCase());
        preparedStatement.setInt(2, id);
        try {
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != 0) {
            System.out.println("Updated ID#: " + id + " was successful.");
        } else {
            System.out.println("ID#: " + id + " does not exist or is no longer available. Unable to update");
        }
    }

    public void updateLastName() throws SQLException {
        System.out.println("Enter ID: ");
        id = 0;
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e){
                System.out.println("Invalid entry. Please enter a valid ID #: ");
            }
        }
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_LAST_NAME);
        int result = -1;
        preparedStatement.setString(1, lastName.toLowerCase());
        preparedStatement.setInt(2, id);
        try {
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != 0) {
            System.out.println("Updated ID#: " + id + " was successful.");
        } else {
            System.out.println("ID#: " + id + " does not exist or is no longer available. Unable to update");
        }
    }

    public void updateFirstAndLastName() throws SQLException {
        System.out.println("Enter ID: ");
        id = 0;
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e){
                System.out.println("Invalid entry. Please enter a valid ID #: ");
            }
        }
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_FIRST_AND_LAST_NAME);
        int result = -1;
        preparedStatement.setString(1, firstName.toLowerCase());
        preparedStatement.setString(2, lastName.toLowerCase());
        preparedStatement.setInt(3, id);
        try {
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != 0) {
            System.out.println("Updated ID#: " + id + " was successful.");
        } else {
            System.out.println("ID#: " + id + " does not exist or is no longer available. Unable to update");
        }
    }



}
