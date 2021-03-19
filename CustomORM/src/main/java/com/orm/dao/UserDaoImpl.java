package com.orm.dao;

import com.orm.model.User;

import java.util.List;

public abstract class UserDaoImpl implements IUserDao {

    private Integer id;
    private String firstName;
    private String lastName;

    public UserDaoImpl(){}

    public UserDaoImpl(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public Boolean createNewUser() {
        return null;
    }

    @Override
    public Boolean createNewUser(String firstName, String lastName) {
        if (firstName.trim().equals("") || firstName == null || lastName.trim().equals("") || lastName == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean updateUserFirstName(Integer id, String firstName) {
        return null;
    }

    @Override
    public Boolean updateUserLastName(Integer id, String lastName) {
        return null;
    }

    @Override
    public Boolean updateUserFirstAndLastName(Integer id, String firstName, String lastName) {
        return null;
    }

    @Override
    public User findByUserId(int Id) {
        return null;
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return null;
    }

    @Override
    public Boolean deleteUserById(Integer id) {
        return null;
    }
}
