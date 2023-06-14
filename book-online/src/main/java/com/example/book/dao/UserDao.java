package com.example.book.dao;

import com.example.book.entity.User;

public interface UserDao {
    int addUser(User user);

    User findUser(User userDto);

    User findUserByAccountAndPass(User user);

    int insertUser(Object ...args);

    User findUserByAccount(String name);
}
