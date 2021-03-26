package com.orm.dao;

import com.orm.config.PostgresTestingDatabase;
import com.orm.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DatabaseDaoImplTest {

    DatabaseDaoImpl databaseDaoImpl = new DatabaseDaoImpl();
    Connection postgresqlConnection = new PostgresTestingDatabase().getConnection();
    User user;
    List<User> users;

    @Mock
    DatabaseDaoImpl databaseDaoImplMock;

    @InjectMocks
    Connection postgresqlConnectionMock = new PostgresTestingDatabase().getConnection();

    DatabaseDaoImplTest() {
    }

    @Test
    void getById(){
        User newUser = new User();
        Mockito.lenient().when(databaseDaoImplMock.getById(1, postgresqlConnectionMock)).thenReturn(user);
        System.out.println(newUser.toString());

    }

    @Test
    void searchByIdTest() {
        user = databaseDaoImpl.getById(1, postgresqlConnection);
        assertEquals(1, user.getId());
        assertEquals("ronaldo", user.getFirstName());
        assertEquals("lopez", user.getLastName());
    }

    @Test
    void searchByFirstName() {
        users = databaseDaoImpl.getByFirstName(postgresqlConnection, "ronaldo");
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    void searchByLastNameTest() {
        users = databaseDaoImpl.getByLastName(postgresqlConnection, "lopez");
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    void searchByFirstAndLastNameTest() {
        users = databaseDaoImpl.getByFirstAndLastName(postgresqlConnection, "blue", "sky");
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    void deleteByIdTest() {
        user = databaseDaoImpl.createUser(postgresqlConnection, "Bob", "Jones");
        int idNumber = user.getId();
        int deleteId = databaseDaoImpl.deleteById(postgresqlConnection, idNumber);
        assertNotEquals(-1, deleteId);
    }


    @Test
    void createTest() {
        String firstName = "bob";
        String lastName = "jones";
        user = databaseDaoImpl.createUser(postgresqlConnection, firstName, lastName);
        assertNotNull(user);
        databaseDaoImpl.deleteById(postgresqlConnection, user.getId());
    }

    @Test
    void updateFieldInColumnTest() {
        user = databaseDaoImpl.createUser(postgresqlConnection, "bob", "jones");
        String tableName = "users";
        String columnName = "first_name";
        int id = user.getId();
        String value = "robert";
        String result = databaseDaoImpl.updateFieldInColumn(postgresqlConnection, tableName, columnName, id, value);
        assertEquals("Update was successful.", result);
        user = databaseDaoImpl.getById(user.getId(), postgresqlConnection);
        assertEquals(value, user.getFirstName());
        databaseDaoImpl.deleteById(postgresqlConnection, user.getId());
    }

    @Test
    void createTableAndDropTableTest() {
        String tableName = "new_table";
        String createTableResult = databaseDaoImpl.createTable(postgresqlConnection, tableName);
        assertEquals("\"" + tableName + "\" table has been successfully created.", createTableResult);
        String dropTableResult = databaseDaoImpl.dropTable(postgresqlConnection, tableName);
        assertEquals("\"" + tableName + "\" table has been successfully dropped.", dropTableResult);
    }

    @Test
    void createAndDeleteColumnTest() {
        String tableName = "users";
        String columnName = "email";
        String dataType = "TEXT";
        String createColumn = databaseDaoImpl.createColumn(postgresqlConnection, tableName, columnName, dataType);
        assertEquals(columnName + " successfully created.", createColumn);
        String deleteColumn = databaseDaoImpl.dropColumn(postgresqlConnection, tableName, columnName);
        assertEquals(columnName + " successfully dropped.", deleteColumn);
    }

    @Test
    void showTables() {
        ArrayList<String> tables = databaseDaoImpl.getTables(postgresqlConnection);
        assertNotNull(tables);
    }

    @Test
    void getColumnNames() {
        String tableName = "users";
        HashMap<String, String> columns = databaseDaoImpl.getColumnNames(postgresqlConnection, tableName);
        assertNotNull(columns);
    }

}