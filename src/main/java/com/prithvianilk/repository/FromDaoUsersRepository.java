package com.prithvianilk.repository;

import com.prithvianilk.tables.daos.UsersDao;
import com.prithvianilk.tables.pojos.Users;

public class FromDaoUsersRepository {
    private final UsersDao usersDao;

    public FromDaoUsersRepository(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void save(Users user) {
        usersDao.insert(user);
    }

    public Users findById(String id) {
        return usersDao.findById(id);
    }
}
