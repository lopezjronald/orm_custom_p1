package com.orm.ui;

import com.orm.config.PostgreDatabase;
import com.orm.config.Queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseService {

    Connection connection = new PostgreDatabase().getConnection();
    Queries queries = new Queries();

    public DatabaseService() throws SQLException {
    }

    public void createTable() {
        try {
            String tableName = queries.createTable(connection, queries.askForTableName());
            int columns = queries.askForColumnAmount();
            String newColumnName = null;
            for (int i = 0; i < columns; i++) {
                newColumnName = queries.askForColumnName();
                System.out.println(queries.createColumn(connection, tableName, newColumnName, queries.askForDataType(), queries.askForConstraint(newColumnName)));
            }

        } catch (SQLException e) {
            System.out.print("Invalid entry. Please try again.");
        }
    }

    public void showTablesInDatabase() throws SQLException {
        ArrayList<String> tables = queries.showTables(connection);
        System.out.println("List of Tables:");
        for (String eachTable : tables) {
            System.out.println(eachTable);
        }
    }

    public void showColumnsInTable() throws SQLException {
        String tableName = queries.askForTableName();
        HashMap<String, String> columns = queries.getColumnNames(connection, tableName);
        System.out.println("List of Columns for table \"" + tableName + "\":");
        for (Map.Entry<String, String> keyValueSet : columns.entrySet()) {
            System.out.println("Column name: " + keyValueSet.getKey() + " | Column Type: " + keyValueSet.getValue());
        }
    }

    public void createColumn() {
        try {
            String tableName = queries.askForTableName();
            String columnName = queries.askForColumnName();
            String dataType = queries.askForDataType();
            String constraint = queries.askForConstraint(columnName);
            String newColumn = queries.createColumn(connection, tableName, columnName, dataType, constraint);
            System.out.println(newColumn + " successfully created.");
        } catch (SQLException e) {
            System.out.println("Invalid entry. Please try again.");
        }
    }

    public void updateFieldInColumnUsingId() {
        while (true) {
            try {
                String tableName = queries.askForTableName();
                String columnName = queries.askForColumnName();
                int id = queries.askForId();
                String fieldValue = queries.askForValue();
                queries.updateFieldInColumn(connection, tableName, columnName, id, fieldValue);
                break;
            } catch (SQLException e) {
                System.out.println("Invalid entry. Please try again.");
            }
        }
    }

    public void searchById() throws SQLException {
        int id = queries.askForId();

    }

}
