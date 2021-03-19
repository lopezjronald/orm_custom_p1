package com.orm.doaimpl;

import com.orm.dao.UserDao;
import com.orm.model.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public Boolean createNewUser() {
        return null;
    }

    @Override
    public Boolean createNewUser(String firstName, String lastName) {
        return null;
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
