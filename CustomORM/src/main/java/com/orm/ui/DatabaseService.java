package com.orm.ui;

import com.orm.config.PostgreDatabase;
import com.orm.dao.DatabaseDaoImpl;

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

    public void searchById() throws SQLException {
        int id = databaseDaoImpl.askForId();

    }

}
