package com.example.book.dao.impl;

import com.example.book.dao.UserDao;
import com.example.book.entity.User;
import com.example.book.util.DBUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.dataSource);

    @Override
    public int addUser(User user) {
        String sql = "insert into t_user(account,password,nickname,avatar,address,create_time) values(?,?,?,?,?,?)";
        Object[] args = {user.getAccount(), user.getPassword(), user.getNickname(), user.getAvatar(), user.getAddress(), user.getCreate_time()};
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public User findUser(User userDto) {
        String sql = "select * from t_user where account = ? and password = ?";
        Object[] args = {userDto.getAccount(), userDto.getPassword()};
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), args);
    }

    @Override
    public User findUserByAccountAndPass(User userDto) {
        String sql = "select * from t_user where account = ? and password = ?";
        Object[] args = {userDto.getAccount(), userDto.getPassword()};
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), args);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public int insertUser(Object... args) {
        String sql = "insert into t_user(account,password) values(?,?)";
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public User findUserByAccount(String account) {
        String sql = "select * from t_user where account = ? ";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), account);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
