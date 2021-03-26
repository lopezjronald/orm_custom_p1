package com.orm.ui;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuService {
    public static final Scanner scanner = new Scanner(System.in);
    DatabaseService databaseService;


    public MenuService() {
        try {
            databaseService = new DatabaseService();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void runProgram() {

        int runProgram = 0;
        while (runProgram == 0) {
            int choice = showMenu();
            runProgram = userChoice(choice);
        }


    }

    public int showMenu() {
        System.out.println();
        System.out.println("\t\t\t***********************");
        System.out.println("\t\t\t****** MAIN MENU ******");
        System.out.println("\t\t\t***********************");
        System.out.println("\nChoose one of the following or Enter -1 to Exit:");
        System.out.println("\nSearch Data");
        System.out.println("1. Show Tables");
        System.out.println("2. Show Columns in a Table");
        System.out.println("3. Show All Users");
        System.out.println("4. Search Data By ID");
        System.out.println("5. Search Data By First Name");
        System.out.println("6. Search Data By Last Name");
        System.out.println("7. Search Data By First and Last Name");
        System.out.println("\nCreate Data");
        System.out.println("8. Create a Table");
        System.out.println("9. Create a Column into a Table");
        System.out.println("10. Create a User");
        System.out.println("\nUpdate Data");
        System.out.println("11. Update a Field By ID");
        System.out.println("\nDelete Data");
        System.out.println("12. Delete a Table");
        System.out.println("13. Delete a Column");
        System.out.println("14. Delete a User ");
        System.out.print("\n===>  ");
        return databaseService.databaseDaoImpl.askForChoice();
    }

    public int userChoice(int choice) {

        switch (choice) {
            case -1:
                return -1;
            case 1:
                databaseService.showTablesInDatabase();
                break;
            case 2:
                databaseService.showColumnsInTable();
                break;
            case 3:
                databaseService.searchAllUserData();
                break;
            case 4:
                databaseService.searchById();
                break;
            case 5:
                databaseService.searchByFirstName();
                break;
            case 6:
                databaseService.searchByLastName();
                break;
            case 7:
                databaseService.searchByFirstAndLastName();
                break;
            case 8:
                databaseService.createTable();
                break;
            case 9:
                databaseService.createColumn();
                break;
            case 10:
                databaseService.createUser();
                break;
            case 11:
                databaseService.updateFieldInColumnUsingId();
                break;
            case 12:
                databaseService.removeTable();
                break;
            case 13:
                databaseService.removeColumn();
                break;
            case 14:
                databaseService.removeUser();
                break;
            default:
                System.out.println("Invalid choice. Please choose one of the following");
        }
        return 0;
    }
}
