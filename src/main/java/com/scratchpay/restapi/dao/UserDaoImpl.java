package com.scratchpay.restapi.dao;

import com.scratchpay.restapi.UserEntity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user) {
        return jdbcTemplate.update("INSERT into userdata (id,name) VALUES (?,?)",new Object[] {user.getId(),user.getName()});
    }

    @Override
    public List<User> getUserList() {
        return jdbcTemplate.query("SELECT * FROM userdata", new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public User getUser(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM userdata where id=?", new BeanPropertyRowMapper<User>(User.class),id);
    }
}
