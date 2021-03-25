package com.orm.ui;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuService {
    public static final Scanner scanner = new Scanner(System.in);
    DatabaseService databaseService = new DatabaseService();


    public MenuService() throws SQLException {
    }

    public void runProgram() throws SQLException {
        int runProgram = 0;
        while (runProgram == 0) {
            int choice = showMenu();
            runProgram = userChoice(choice);
        }


    }


    public int showMenu() {
        System.out.println("Choose one of the following:");
        System.out.println("1. Show Tables");
        System.out.println("2. Show Columns in a Table");
        System.out.println("3. Create a Table");
        System.out.println("4. Create a Column into a Table");
        System.out.println("5. Update Field By ID");
        System.out.println("To exit, enter -1");
        return databaseService.queries.askForChoice();
    }

    public int userChoice(int choice) throws SQLException {
        System.out.println("Choice Option: " + choice);
        switch (choice) {
            case -1:
                return -1;
            case 1:
                databaseService.showTablesInDatabase();
                return 0;
            case 2:
                databaseService.showColumnsInTable();
                return 0;
            case 3:
                databaseService.createTable();
                return 0;
            case 4:
                databaseService.createColumn();
                return 0;
            case 5:
                databaseService.updateFieldInColumnUsingId();
                return 0;
            default:
                System.out.println("Invalid choice");
                return 0;
        }
    }




}
