package com.orm.model;

import com.orm.dao.UserDaoImpl;

public class User extends UserDaoImpl {

    public User() {
        super();
    }

    public User(Integer id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

}
