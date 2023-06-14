package com.example.book.service.impl;

import com.example.book.dao.BookDao;
import com.example.book.dao.impl.BookDaoImpl;
import com.example.book.entity.Book;
import com.example.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();
    @Override
    public List<Book> getBookList() {
        return bookDao.getBookList();
    }
}
