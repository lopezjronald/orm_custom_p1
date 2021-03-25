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
        System.out.println("Choose one of the following or Enter -1 to Exit:");
        System.out.println("\nSearch Dataa");
        System.out.println("1. Show Tables");
        System.out.println("2. Show Columns in a Table");
        System.out.println("3. Search Data By ID");
        System.out.println("4. Search Data By First Name");
        System.out.println("5. Search Data By Last Name");
        System.out.println("6. Search Data By First and Last Name");
        System.out.println("\nCreate Data");
        System.out.println("7. Create a Table");
        System.out.println("8. Create a Column into a Table");
        System.out.println("9. Create a User");
        System.out.println("\nUpdate Data");
        System.out.println("10. Update a Field By ID");
        System.out.println("\nDelete Data");
        System.out.println("11. Remove a Table");
        System.out.println("12. Remove a Column");
        return databaseService.databaseDaoImpl.askForChoice();
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

    public static void main(String[] args) throws SQLException {
        MenuService ms = new MenuService();
        ms.showMenu();
    }



}
