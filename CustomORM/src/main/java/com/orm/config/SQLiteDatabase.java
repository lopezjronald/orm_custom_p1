package com.orm.config;

import java.sql.*;

public class SQLiteDatabase {
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Shadow\\Desktop\\Projects\\custom-java-orm\\CustomORM\\" + DB_NAME;
    public static final String TABLE_USER = "user";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            /************************** CRUD OPERATIONS ******************************************************/

            ///////////// DROP/CREATE TABLE STATEMENT CLAUSE ///////////////////////////////////////
            statement.execute("DROP TABLE IF EXISTS "
                    + TABLE_USER);
            statement.execute("CREATE TABLE IF NOT EXISTS "
                    + TABLE_USER + " ("
                    + COLUMN_FIRST_NAME + " TEXT, "
                    + COLUMN_LAST_NAME + " TEXT, "
                    + COLUMN_EMAIL + " TEXT)");

            /////////////// INSERT INTO TABLE CLAUSE ////////////////////////////////
            insertContact(statement, "jeff", "lopez", "jeff@gmail.com");
            insertContact(statement, "chris", "columbus", "chris@gmail.com");
            insertContact(statement, "ron", "mcdonald", "ron@gmail.com");
            insertContact(statement, "joyce", "amor", "joyce@gmail.com");
            insertContact(statement, "mike", "jackson", "mike@gmail.com");
//
//
//            /////////////////////// UPDATE STATEMENT CLAUSE //////////////////////
            statement.execute("UPDATE "
                    + TABLE_USER + " SET "
                    + COLUMN_LAST_NAME + " = 'jordan'  WHERE "
                    + COLUMN_FIRST_NAME + " = 'mike'");
//
//            //////////////////// DELETE STATEMENT CLAUSE ///////////////////////////
            statement.execute("DELETE FROM "
                    + TABLE_USER + " WHERE "
                    + COLUMN_FIRST_NAME + " = 'joyce'");
//
//            ////////////////////// SELECT STATEMENT CLAUSE //////////////////////////
            ResultSet results = statement.executeQuery("SELECT * FROM "
                    + TABLE_USER);
            while (results.next()) {
                if (results.getString(COLUMN_FIRST_NAME).equals("jeff"))
                    System.out.println(results.getString(COLUMN_FIRST_NAME) +
                            ", " + results.getString(COLUMN_LAST_NAME)
                            + ", " + results.getString(COLUMN_EMAIL));

            }
            results.close();
//
            System.out.println("******************************************");

            ResultSet resultsTwo = statement.executeQuery("SELECT * FROM "
                    + TABLE_USER);
            while (resultsTwo.next()) {
                System.out.println(resultsTwo.getString(COLUMN_FIRST_NAME) +
                        ", " + resultsTwo.getString(COLUMN_LAST_NAME)
                        + ", " + resultsTwo.getString(COLUMN_EMAIL));

            }

            resultsTwo.close();
//            /****************** END OF CRUD OPERATIONS *********************************************************/
//
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }


    }

    private static void insertContact(Statement statement, String firstName, String lastName, String email) throws SQLException {
        statement.execute("" +
                "INSERT INTO "
                + TABLE_USER + " ("
                + COLUMN_FIRST_NAME + ", "
                + COLUMN_LAST_NAME + ", "
                + COLUMN_EMAIL + ") " +
                "VALUES('"
                + firstName + "', '"
                + lastName + "', '"
                + email + "')");
    }
}


