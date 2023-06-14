package com.example.book.dao.impl;

import com.example.book.dao.BookDao;
import com.example.book.entity.Book;
import com.example.book.entity.User;
import com.example.book.util.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDaoImpl implements BookDao {

    private  JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.dataSource);

    @Override
    public List<Book> getBookList() {
        String sql = "select id,name,cover,author from t_book";
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return books;
    }
}
