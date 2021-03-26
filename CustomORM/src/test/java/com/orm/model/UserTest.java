package com.orm.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserTest {

    User user;

    @BeforeEach
    void createUser()  {
        user = new User();
        int id = 5555;
        String firstName = "blue";
        String lastName = "sky";
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
    }

    @Test
    void createUserTest() {
        int id = 9999;
        String firstName = "Peter";
        String lastName = "Pan";
        User newUser = new User(9999, "Peter", "Pan");
        assertAll("Test User Setup",
                () -> assertEquals(id, newUser.getId()),
                () -> assertEquals(firstName, newUser.getFirstName()),
                () -> assertEquals(lastName, newUser.getLastName()));
    }

    @Test
    void getIdTest()  {
        assertEquals(5555, user.getId());
    }

    @Test
    void setIdTest()  {
        int id = 9999;
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    void getFirstNameTest()  {
        Assertions.assertNotNull(user.getLastName());
    }

    @Test
    void setFirstNameTest()  {
        String firstName = "ronald";
        user.setFirstName(firstName);
        assertEquals(firstName, user.getFirstName());
    }

    @Test
    void getLastNameTest()  {
        Assertions.assertNotNull(user.getLastName());
    }

    @Test
    void setLastNameTest()  {
        String lastName = "Lopez";
        user.setLastName(lastName);
        assertEquals(lastName, user.getLastName());
    }

    @Test
    void toStringTest() {
        int id = 999;
        String firstName = "Snoop";
        String lastName = "Dogg";
        user = new User(id, firstName, lastName);
        assertEquals("User(id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +")", user.toString());
    }

    @Test
    void HashCodeTest() {
        int id = 999;
        String firstName = "Snoop";
        String lastName = "Dogg";
        user = new User(id, firstName, lastName);
        assertEquals(433180500, user.hashCode());
    }

    @Test
    void EqualsToTest() {
        int id = 999;
        String firstName = "Snoop";
        String lastName = "Dogg";
        user = new User(id, firstName, lastName);
        User newUser = user;
        assertEquals(newUser, user);
    }

    @Test
    void canEqualToTest() {
        int id = 999;
        String firstName = "Snoop";
        String lastName = "Dogg";
        user = new User(id, firstName, lastName);
        User newUser = user;
        assertTrue(user.canEqual(newUser));
    }

}