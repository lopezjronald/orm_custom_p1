package com.orm.ui;

import com.orm.config.PostgreDatabase;
import com.orm.dao.DatabaseDaoImpl;
import com.orm.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseService {

    Connection connection = new PostgreDatabase().getConnection();
    DatabaseDaoImpl databaseDaoImpl = new DatabaseDaoImpl();

    public DatabaseService() throws SQLException {
    }

    public void createTable() {
        String tableName = databaseDaoImpl.createTable(connection, databaseDaoImpl.askForTableName());
        int columns = databaseDaoImpl.askForColumnAmount();
        String newColumnName = null;
        for (int i = 0; i < columns; i++) {
            newColumnName = databaseDaoImpl.askForColumnName();
            System.out.println(databaseDaoImpl.createColumn(connection, tableName, newColumnName, databaseDaoImpl.askForDataType(), databaseDaoImpl.askForConstraint(newColumnName)));
        }

    }

    public void showTablesInDatabase() {
        ArrayList<String> tables = databaseDaoImpl.getTables(connection);
        System.out.println("List of Tables:");
        for (String eachTable : tables) {
            System.out.println(eachTable);
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

    public void createColumn() {
        String tableName = databaseDaoImpl.askForTableName();
        String columnName = databaseDaoImpl.askForColumnName();
        String dataType = databaseDaoImpl.askForDataType();
        String constraint = databaseDaoImpl.askForConstraint(columnName);
        String newColumn = databaseDaoImpl.createColumn(connection, tableName, columnName, dataType, constraint);
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
        String firstName = databaseDaoImpl.askForName();
        ArrayList<User> users = databaseDaoImpl.getByFirstName(connection, firstName);
        if (users.size() > 0) {
            for (User eachUser: users) {
                System.out.println(eachUser.toString());
            }
        } else {
            System.out.println("There are no users with the first name " + firstName);
        }
    }

    public void searchByLastName(){
        String lastName = databaseDaoImpl.askForName();
        List<User> users = databaseDaoImpl.getByLastName(connection, lastName);
        if (users.size() > 0) {
            for (User eachUser: users) {
                System.out.println(eachUser.toString());
            }
        } else {
            System.out.println("There are no users with the last name " + lastName);
        }
    }

    public void searchByFirstAndLastName(){}

    public void createUser(){}

    public void removeTable(){}

    public void removeColumn(){}

    public void removeUser(){}

}
