package com.orm.config;

import java.sql.*;

public class Database {

    public static final String url = "jdbc:postgresql://postgresql-class.custom-orm.cnftml2dw6bt.us-east-2.rds.amazonaws.com";
    public static final String username = "postgres";
    public static final String password = "password";
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Shadow\\Desktop\\Projects\\custom-java-orm\\CustomORM\\" + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

/************************** CRUD OPERATIONS ******************************************************

///////////// DROP/CREATE TABLE STATEMENT CLAUSE ///////////////////////////////////////
            statement.execute("DROP TABLE IF EXISTS "
                    + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS "
                    + TABLE_CONTACTS + " ("
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_PHONE + " INTEGER, "
                    + COLUMN_EMAIL + " TEXT)");

 /////////////// INSERT INTO TABLE CLAUSE ////////////////////////////////
            statement.execute("INSERT INTO "
                    + TABLE_CONTACTS + " ("
                    + COLUMN_NAME + ", "
                    + COLUMN_PHONE + ", "
                    + COLUMN_EMAIL + ") " +
                    "VALUES('jeff', 1911915198, 'jeff@gmail.com')," +
                    "('chris', 011548098, 'chris@gmail.com')," +
                    "('joyce', 519688821, 'joyce@gmail.com')," +
                    "('mike', 165056180, 'mike@gmail.com')");

/////////////////////// UPDATE STATEMENT CLAUSE //////////////////////
             statement.execute("UPDATE "
                    + TABLE_CONTACTS + " SET "
                    + COLUMN_PHONE + " = 9999999 WHERE "
                    + COLUMN_NAME + " = 'mike'");

 //////////////////// DELETE STATEMENT CLAUSE ///////////////////////////
                    statement.execute("DELETE FROM "
                    + TABLE_CONTACTS + " WHERE "
                    + COLUMN_NAME + " = 'joyce'");

 ////////////////////// SELECT STATEMENT CLAUSE //////////////////////////
            ResultSet results = statement.executeQuery("SELECT * FROM "
                    + TABLE_CONTACTS);
            while (results.next()) {
                if (results.getString(COLUMN_NAME).equals("jeff"))
                    System.out.println(results.getString(COLUMN_NAME) +
                            ", " + results.getInt(COLUMN_PHONE)
                            + ", " + results.getString(COLUMN_EMAIL));
            }
            results.close();

 ****************** END OF CRUD OPERATIONS *********************************************************/

            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Soemthing went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public final Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }


}
