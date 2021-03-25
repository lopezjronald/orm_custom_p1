package com.orm.dao;

import com.orm.model.User;

import java.sql.*;
import java.util.*;

public class DatabaseDaoImpl implements DatabaseDao{

    public final static Scanner scanner = new Scanner(System.in);

    /******************** COLUMN TABLE *******************/
    public static final String TABLE_NAME = "users";

    /********************* COLUMN NAMES ****************/
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";

    /******************** CREATE TABLE QUERY ****************/
    public static final String QUERY_CREATE_TABLE_PART_1 = "CREATE TABLE IF NOT EXISTS ";
    public static final String QUERY_CREATE_TABLE_PART_2 = "(id SERIAL PRIMARY KEY)";

    /******************** ADD COLUMN QUERY ****************/
    public static final String QUERY_CREATE_COLUMN_PART_1 = "ALTER TABLE ";
    public static final String QUERY_CREATE_COLUMN_PART_2 = " ADD COLUMN  ";

    /******************** DROP TABLE QUERY ****************/
    public static final String QUERY_DROP_TABLE = "DROP TABLE IF EXISTS ";

    /******************** DROP COLUMN QUERY ****************/
    public static final String QUERY_DROP_COLUMN_PART_1 = "ALTER TABLE ";
    public static final String QUERY_DROP_COLUMN_PART_2 = " DROP COLUMN ";

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

    public DatabaseDaoImpl() throws SQLException {
    }

    /***************** SELECT QUERIES ****************/
    @Override
    public User getById(int id, Connection connection) {
        try {
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
        } catch (SQLException e) {
            System.out.println("There was a problem with your transaction");
            return null;
        } catch (Exception e) {
            System.out.println("ID does not exist or is no longer in the system.");
            return null;
        }
    }

    @Override
    public ArrayList<User> getByFirstName(Connection connection, String firstName) {
        try {
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
        } catch (SQLException e) {
            System.out.println("Name does not exist or is no longer in the system");
        }
        return null;
    }

    @Override
    public List<User> getByLastName(Connection connection, String lastName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_LAST_NAME);
            preparedStatement.setString(1, lastName.toLowerCase().trim());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    User user = new User();
                    users.add(user);
                }
                resultSet.close();
                preparedStatement.close();
                return users;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Invalid entry");
        }
        return null;
    }

    @Override
    public List<User> getByFirstAndLastName(Connection connection, String firstName, String lastName)  {
        try {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**************** DELETE QUERY **********************/
    @Override
    public int deleteById(Connection connection, int id)  {
        try {
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
            return result;
        } catch (SQLException e) {
            System.out.println("ID #: " + id + " does not exist or is no longer available.");
            return -1;
        }

    }

    /**************** CREATE QUERY **********************/
    @Override
    public User create(Connection connection, String firstName, String lastName)  {
        try {
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
                user = getById(resultSet.getInt(1), connection);
            }

            if (result != 0) {
                System.out.println("Entry was successful");
                preparedStatement.close();
                return user;
            } else {
                System.out.println("ID #: " + id + " does not exist or is no longer available.");
                return new User();
            }
        } catch (SQLException e) {
            System.out.println("Sorry, unable to create the query due to syntax.");
            return null;
        }
    }

    /********************** UPDATE QUERIES *****************/
    @Override
    public String updateFieldInColumn(Connection connection, String tableName, String columnName, int id, String value)  {
        try {
            int result = -1;
            String status = null;

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
                return status;
            }
            if (result != 0) {
                status = "Update was successful.";
                System.out.println(status);
                preparedStatement.close();
                return status;
            } else {
                status = "Something wrong occurred. Id does not exist or is no longer available. Unable to update";
                System.out.println(status);
            }
            return status;
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    /******************** CREATE TABLE QUERY ************************/
    @Override
    public String createTable(Connection connection, String tableName) {
        try {
            String tableCreated = null;
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE_TABLE_PART_1 + tableName + QUERY_CREATE_TABLE_PART_2);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return tableName + " successfully created.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    /******************** DROP TABLE QUERY ************************/
    @Override
    public String dropTable(Connection connection, String tableName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DROP_TABLE + tableName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return ("Table " + tableName +  " successfully dropped.");
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    @Override
    public String createColumn(Connection connection, String tableName, String columnName, String dataType, String
            constraint) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE_COLUMN_PART_1 + tableName + QUERY_CREATE_COLUMN_PART_2 + columnName + " " + dataType + " " + constraint);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return columnName + " successfully created.";
        } catch (SQLException e) {
            System.out.println("Sorry, you have entered an invalid column entry.");
            return e.getMessage();
        }
    }

    @Override
    public String dropColumn(Connection connection, String tableName, String columnName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DROP_COLUMN_PART_1 + tableName + QUERY_DROP_COLUMN_PART_2 + columnName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return columnName + " successfully dropped.";
        } catch (SQLException e) {
            System.out.println("Sorry, column does not exist or is not longer in the system.");
            return e.getMessage();
        }
    }

    @Override
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

    @Override
    public int askForId() {
        System.out.print("Enter ID: ");
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid entry. Please enter a valid ID #: ");
            } catch (UnknownFormatConversionException e) {
                System.out.print("Something wrong occurred in the system.");
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        return -1;
    }

    @Override
    public String askForTableName() {
        System.out.print("Enter table name: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    @Override
    public String askForName() {
        System.out.print("Enter name: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    @Override
    public String askForColumnName() {
        System.out.print("Enter column name: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    @Override
    public String askForDataType() {
        System.out.print("Enter Data Type (Double, Text, Serial, Integer, Boolean, Character): ");
        return scanner.nextLine();
    }

    @Override
    public String askForConstraint(String columnName) {
        String constraint = "";
        int numberOfConstraints = askForConstraintAmount(columnName);
        int beginning = 0;
        for (int i = 1; i <= numberOfConstraints; i++) {
            if (beginning == 0) {
                constraint += askForConstraintType();
                beginning = numberOfConstraints;
            } else if (i == beginning) {
                constraint += " " + askForConstraintType() + ", ";
            } else {
                constraint += " " + askForConstraintType();
            }
        }
        return constraint;
    }

    @Override
    public int askForConstraintAmount(String columnName) {
        while (true) {
            System.out.print("How many constraints for " + columnName + ": ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Invalid entry. ");
            }
        }
    }

    @Override
    public String askForConstraintType() {
        System.out.print("Enter Constraint: (Not_Null, Primary_Key, Foreign_Key, Unique): ");
        return scanner.nextLine();
    }

    @Override
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

    @Override
    public ArrayList<String> getTables(Connection connection) {
        try {
            ArrayList<String> tables = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LIST_ALL_TABLES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tables.add(resultSet.getString(1));
            }
            preparedStatement.close();
            return tables;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public HashMap<String, String> getColumnNames(Connection connection, String tableName) {
        try {
            HashMap<String, String> columns = new HashMap<>();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_LIST_ALL_COLUMN_NAMES_FROM_TABLE + tableName);
            ResultSetMetaData metaData = preparedStatement.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columns.put(metaData.getColumnName(i), metaData.getColumnTypeName(i));
            }
            preparedStatement.close();
            return columns;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int askForChoice() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (InputMismatchException e) {
                System.out.print("Invalid entry. Please choose one of the choices: ");
            } catch (Exception e) {
                System.out.print("Invalid entry. Please choose one of the choices: ");
            }
        }
    }
}
