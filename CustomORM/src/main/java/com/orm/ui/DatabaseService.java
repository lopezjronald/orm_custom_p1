package com.orm.ui;

import com.orm.config.PostgreDatabase;
import com.orm.dao.DatabaseDaoImpl;
import com.orm.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseService {

    Connection connection = new PostgreDatabase().getConnection();
    DatabaseDaoImpl databaseDaoImpl = new DatabaseDaoImpl();

    public DatabaseService() throws SQLException {
    }

    public void showTablesInDatabase() {
        try {
            ArrayList<String> tables = databaseDaoImpl.getTables(connection);
            System.out.println("List of Tables:");
            for (String eachTable : tables) {
                System.out.println(eachTable);
            }
        } catch (NullPointerException e) {
            System.out.println("Sorry. The table request does not exist or is no longer available.");
        }
    }

    public void showColumnsInTable() {
        String tableName = databaseDaoImpl.askForTableName();
        HashMap<String, String> columns = databaseDaoImpl.getColumnNames(connection, tableName);
        System.out.println("List of Columns for table \"" + tableName + "\":");
        for (Map.Entry<String, String> keyValueSet : columns.entrySet()) {
            System.out.println("Column name: " + keyValueSet.getKey() + " | Column Type: " + keyValueSet.getValue());
        }
    }

    public void searchById() {
        int id = databaseDaoImpl.askForId();
        User newUser = databaseDaoImpl.getById(id, connection);
        if (newUser.getId() != null) {
            System.out.println(newUser.toString());
        } else {
            System.out.println("Sorry. This user does not exist or is no longer in the system");
        }
    }

    public void searchByFirstName() {
        String firstName = databaseDaoImpl.askForName("first");
        ArrayList<User> users = databaseDaoImpl.getByFirstName(connection, firstName);
        if (users.size() > 0) {
            System.out.println("List of First Names: \"" + firstName + "\"");
            for (User eachUser : users) {
                System.out.println(eachUser.toString());
            }
        } else {
            System.out.println("There are no users with the first name \"" + firstName + "\"");
        }
    }

    public void searchByLastName() {
        String lastName = databaseDaoImpl.askForName("last");
        ArrayList<User> users = databaseDaoImpl.getByLastName(connection, lastName);
        if (users.size() > 0) {
            System.out.println("List of Last Names: \"" + lastName + "\"");
            for (User eachUser : users) {
                System.out.println(eachUser.toString());
            }
        } else {
            System.out.println("There are no users with the last name " + lastName);
        }
    }
    public void searchByFirstAndLastName() {
        String firstName = databaseDaoImpl.askForName("first");
        String lastName = databaseDaoImpl.askForName("last");
        ArrayList<User> users = databaseDaoImpl.getByFirstAndLastName(connection, firstName, lastName);
        if (users.size() > 0) {
            System.out.println("List of Last Names: \"" + lastName + "\"");
            for (User eachUser : users) {
                System.out.println(eachUser.toString());
            }
        } else {
            System.out.println("There are no users with the first name \"" + firstName + "\" and last name \"" + lastName + "\"");
        }
    }

    public void createTable() {
        String tableName = databaseDaoImpl.askForTableName();
        String result = databaseDaoImpl.createTable(connection, tableName);
        int columns = databaseDaoImpl.askForColumnAmount();
        String newColumnName = null;
        String dataType = null;
        if (columns != 0){
            for (int i = 0; i < columns; i++) {
                newColumnName = databaseDaoImpl.askForColumnName();
                dataType = databaseDaoImpl.askForDataType();
                System.out.println(databaseDaoImpl.createColumn(connection, tableName, newColumnName, dataType));
            }
        } else {
            System.out.println(result);
        }
    }

    public void searchAllUserData() {
        String tableName = "users";
        ArrayList<User> users = databaseDaoImpl.getAllUsers(connection, tableName);
        if (users.size() > 0) {
            System.out.println("User Data for Table: \"" + tableName + "\"");
            for (User eachUser : users) {
                System.out.println(eachUser.toString());
            }
        } else {
            System.out.println("\"" + tableName + "\" table does not exist or is no longer available");
        }

    }

    public void createColumn() {
        String tableName = databaseDaoImpl.askForTableName();
        String columnName = databaseDaoImpl.askForColumnName();
        String dataType = databaseDaoImpl.askForDataType();
        String newColumn = databaseDaoImpl.createColumn(connection, tableName, columnName, dataType);
        System.out.println(newColumn + " successfully created.");
    }

    public void updateFieldInColumnUsingId() {
        while (true) {
            try {
                String tableName = databaseDaoImpl.askForTableName();
                String columnName = databaseDaoImpl.askForColumnName();
                int id = databaseDaoImpl.askForId();
                String fieldValue = databaseDaoImpl.askForValue();
                databaseDaoImpl.updateFieldInColumn(connection, tableName, columnName, id, fieldValue);
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void createUser() {
        String firstName = databaseDaoImpl.askForName("first");
        String lastName = databaseDaoImpl.askForName("last");
        User newUser = databaseDaoImpl.createUser(connection, firstName, lastName);
    }

    public void removeTable() {
        String tableName = databaseDaoImpl.askForTableName();
        String result = databaseDaoImpl.dropTable(connection, tableName);
        System.out.println(result);
    }

    public void removeColumn() {
        String tableName = databaseDaoImpl.askForTableName();
        String columnName = databaseDaoImpl.askForColumnName();
        String result = databaseDaoImpl.dropColumn(connection, tableName, columnName);
        System.out.println(result);
    }

    public void removeUser() {
        int id = databaseDaoImpl.askForId();
        databaseDaoImpl.deleteById(connection, id);
    }

}
