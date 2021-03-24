package com.orm.model;

import com.orm.config.PostgreDatabase;
import com.orm.config.Queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Table {

    Connection connection = new PostgreDatabase().getConnection();
    Queries queries = new Queries();

    public Table() throws SQLException {
    }

    public void createTable() throws SQLException {
        String tableName = queries.createTable(connection, queries.askForTableName());
        int columns = queries.askForColumnAmount();
        int count = 0;
        String newColumnName = null;
        for (int i = 0; i < columns; i++) {
            newColumnName = queries.askForColumnName();
            System.out.println(queries.createColumn(connection, tableName, newColumnName, queries.askForDataType(), queries.getFullConstraintRequest(newColumnName)));
        }
    }

    public void showTablesInDatabase() throws SQLException {
        ArrayList<String> tables = queries.showTables(connection);
        System.out.println("List of Tables:");
        for (String eachTable: tables) {
            System.out.println(eachTable);
        }
    }

    public void showColumnsInTable() throws SQLException {
        String tableName = queries.askForTableName();
        HashMap <String, String> columns = queries.getColumnNames(connection, tableName);
        System.out.println("List of Columns for table \"" + tableName + "\":");
        for (Map.Entry<String, String> keyValueSet: columns.entrySet()) {
            System.out.println("Column name: " + keyValueSet.getKey() + " | Column Type: " + keyValueSet.getValue() );
        }
    }


}
