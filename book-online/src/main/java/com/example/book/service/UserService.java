package com.example.book.service;

import com.example.book.entity.Book;
import com.example.book.entity.User;

import java.util.List;

public interface UserService {
    User findUserByAccountAndPass(User user);

    boolean signIn(User user);

    boolean isExist(String account);


}
