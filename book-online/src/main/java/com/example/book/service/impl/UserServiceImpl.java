package com.example.book.service.impl;

import com.example.book.dao.UserDao;
import com.example.book.dao.impl.UserDaoImpl;
import com.example.book.entity.User;
import com.example.book.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User findUserByAccountAndPass(User user) {
        return userDao.findUserByAccountAndPass(user);
    }

    @Override
    public boolean signIn(User user) {
        return userDao.insertUser(user.getAccount(), user.getPassword()) > 0;
    }

    @Override
    public boolean isExist(String account) {
        return userDao.findUserByAccount(account) != null;
    }
}
