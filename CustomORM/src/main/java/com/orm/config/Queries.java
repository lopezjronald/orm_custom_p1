package com.orm.config;

import com.orm.model.User;

import java.sql.*;
import java.util.*;

public class Queries {

    public final static Scanner scanner = new Scanner(System.in);
    /******************** COLUMN TABLE *******************/
    public static final String TABLE_NAME = "users";
    /********************* COLUMN NAMES ****************/
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    /******************** CREATE TABLE QUERY ****************/
    public static final String QUERY_CREATE_TABLE_PART_1 = "CREATE TABLE IF NOT EXISTS ";
    public static final String QUERY_CREATE_TABLE_PART_2 = "()";
    /******************** ADD COLUMN QUERY ****************/
    public static final String QUERY_CREATE_COLUMN_PART_1 = "ALTER TABLE ";
    public static final String QUERY_CREATE_COLUMN_PART_2 = " ADD COLUMN  ";
    /******************** DROP TABLE QUERY ****************/
    public static final String QUERY_DROP_TABLE = "DROP TABLE IF EXISTS ";
    /******************** GET ALL TABLE NAMES QUERY ****************/
    public static final String QUERY_LIST_ALL_TABLES = "SELECT table_name FROM information_schema.tables WHERE table_schema='public'";
    /******************** GET ALL COLUMN NAMES FROM TABLE QUERY ****************/
    public static final String QUERY_LIST_ALL_COLUMN_NAMES_FROM_TABLE = "SELECT * FROM ";
    /****************** SEARCH QUERIES *******************/
    public static final String QUERY_SEARCH_ID = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_ID + " = ? ORDER BY " +
            COLUMN_LAST_NAME + ", " + COLUMN_FIRST_NAME;
    public static final String QUERY_SEARCH_FIRST_NAME = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_FIRST_NAME + " = ? ORDER BY " +
            COLUMN_LAST_NAME + ", " + COLUMN_FIRST_NAME;
    public static final String QUERY_SEARCH_LAST_NAME = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_LAST_NAME + " = ? ORDER BY " +
            COLUMN_LAST_NAME + ", " + COLUMN_FIRST_NAME;
    public static final String QUERY_SEARCH_FIRST_AND_LAST_NAME = "SELECT " +
            COLUMN_ID + ", " +
            COLUMN_FIRST_NAME + ", " +
            COLUMN_LAST_NAME + " FROM " +
            TABLE_NAME + " WHERE " +
            COLUMN_FIRST_NAME + " = ? AND " +
            COLUMN_LAST_NAME + " = ? ORDER BY " +
            COLUMN_LAST_NAME + ", " + COLUMN_FIRST_NAME;
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
    public static final String QUERY_UPDATE_FIELD_IN_COLUMN_PART_1 = "UPDATE ";
    public static final String QUERY_UPDATE_FIELD_IN_COLUMN_PART_2 = " SET ";
    public static final String QUERY_UPDATE_FIELD_IN_COLUMN_PART_3 = " = ? WHERE id = ?";

    public static Integer id;

    public Queries() throws SQLException {
    }

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

    public ArrayList<User> searchByFirstName(Connection connection, String firstName) throws SQLException {
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

    public List<User> searchByLastName(Connection connection, String lastName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_LAST_NAME);
        preparedStatement.setString(1, lastName.toLowerCase().trim());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet != null) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                users.add(user);
            }
            resultSet.close();
            preparedStatement.close();
            return users;
        }
        preparedStatement.close();
        return null;
    }

    public List<User> searchByFirstAndLastName(Connection connection, String firstName, String lastName) throws SQLException {
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
    public void deleteById(Connection connection, int id) throws SQLException {
        connection.setAutoCommit(false);
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
    public User create(Connection connection, String firstName, String lastName) throws SQLException {
        while (true) {
            firstName = firstName.trim();
            if (firstName != "" || firstName != null) {
                break;
            } else {
                System.out.print("Invalid entry. Please enter a first name: ");
            }
        }

        while (true) {
            lastName = lastName.trim();
            if (lastName != "" || lastName != null) {
                break;
            } else {
                System.out.print("Invalid entry. Please enter a first name: ");
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
    public void updateFieldInColumn(Connection connection, String tableName, String columnName, int id, String value) throws SQLException {
        int result = -1;

        PreparedStatement preparedStatement = connection.prepareStatement(
                QUERY_UPDATE_FIELD_IN_COLUMN_PART_1 + tableName +
                        QUERY_UPDATE_FIELD_IN_COLUMN_PART_2 + columnName +
                        QUERY_UPDATE_FIELD_IN_COLUMN_PART_3);
        preparedStatement.setString(1, value);
        preparedStatement.setInt(2, id);
        try {
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != 0) {
            System.out.println("Update was successful.");
            preparedStatement.close();
        } else {
            System.out.println("Something wrong occurred. Id does not exist or is no longer available. Unable to update");
        }
    }


//    public User updateFirstAndLastName(Connection connection, Integer id, String firstName, String lastName) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_FIRST_AND_LAST_NAME);
//        int result = -1;
//        preparedStatement.setString(1, firstName.toLowerCase().trim());
//        preparedStatement.setString(2, lastName.toLowerCase().trim());
//        preparedStatement.setInt(3, id);
//        try {
//            result = preparedStatement.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        if (result != 0) {
//            System.out.println("Updated ID#: " + id + " was successful.");
//            preparedStatement.close();
//        } else {
//            System.out.println("ID#: " + id + " does not exist or is no longer available. Unable to update");
//        }
//        return searchById(id, connection);
//    }

    /******************** CREATE TABLE QUERY ************************/
    public String createTable(Connection connection, String tableName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE_TABLE_PART_1 + tableName + QUERY_CREATE_TABLE_PART_2);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return tableName;
    }

    /******************** DROP TABLE QUERY ************************/
    public String dropTable(Connection connection, String tableName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DROP_TABLE + tableName);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return ("Table dropped.");
    }

    public String createColumn(Connection connection, String tableName, String columnName, String dataType, String constraint) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE_COLUMN_PART_1 + tableName + QUERY_CREATE_COLUMN_PART_2 + columnName + " " + dataType + " " + constraint);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return (columnName + " successfully entered");
    }

    public String askForValue() {
        while (true) {
            System.out.print("Enter new value: ");
            String name = scanner.nextLine();
            if (!name.equalsIgnoreCase(""))
                return name.trim().toLowerCase();
            else
                System.out.print("No entry. Please enter a value: ");
        }
    }

    public int askForId() {
        System.out.print("Enter ID: ");
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (InputMismatchException e) {
                System.out.print("Invalid entry. Please enter a valid ID #: ");
            } catch (Exception e) {
                System.out.print("Something wrong occurred in the system. Please try again.");
            }
        }
    }

    public String askForTableName() {
        System.out.print("Enter table name: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    public String askForColumnName() {
        System.out.print("Enter column name: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    public String askForDataType() {
        System.out.print("Enter Data Type (Double, Text, Serial, Integer, Boolean, Character): ");
        return scanner.nextLine();
    }

    public String getFullConstraintRequest(String columnName) {
        String constraint = "";
        int numberOfConstraints = askForConstraintAmount(columnName);
        int beginning = 0;
        for (int i = 1; i <= numberOfConstraints; i++) {
            if (beginning == 0) {
                constraint += askForConstraints();
                beginning = numberOfConstraints;
            } else if (i == beginning) {
                constraint += " " + askForConstraints() + ", ";
            } else {
                constraint += " " + askForConstraints();
            }
        }
        return constraint;
    }

    public int askForConstraintAmount(String columnName) {
        while (true) {
            System.out.print("How many constraints for " + columnName + ": ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (InputMismatchException e) {
                System.out.print("Invalid entry. Please enter an integer value: ");
            }
        }
    }

    public String askForConstraints() {
        System.out.print("Enter Constraint: (Not_Null, Primary_Key, Foreign_Key, Unique): ");
        return scanner.nextLine();
    }

    public int askForColumnAmount() {
        System.out.print("How many columns would you like to create: ");
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim().toLowerCase());
            } catch (InputMismatchException e) {
                System.out.print("Invalid Entry. Please enter an integer value: ");
            }
        }
    }

    public ArrayList<String> showTables(Connection connection) throws SQLException {
        ArrayList <String> tables = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LIST_ALL_TABLES);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            tables.add(resultSet.getString(1));
        }
        preparedStatement.close();
        return tables;
    }

    public HashMap<String, String> getColumnNames(Connection connection, String tableName) throws SQLException {
        HashMap<String, String> columns = new HashMap<>();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LIST_ALL_COLUMN_NAMES_FROM_TABLE + tableName);
        ResultSetMetaData metaData = preparedStatement.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            columns.put(metaData.getColumnName(i), metaData.getColumnTypeName(i));
        }
        preparedStatement.close();
        return columns;
    }


}
