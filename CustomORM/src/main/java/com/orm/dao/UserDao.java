package com.orm.dao;

import com.orm.model.User;

import java.util.List;

public interface UserDao {

    //    CRUD Operations
    Boolean createNewUser();

    Boolean createNewUser(String firstName, String lastName);

    Boolean updateUserFirstName(Integer id, String firstName);

    Boolean updateUserLastName(Integer id, String lastName);

    Boolean updateUserFirstAndLastName(Integer id, String firstName, String lastName);

    User findByUserId(int Id);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    Boolean deleteUserById(Integer id);


}
