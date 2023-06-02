package com.scratchpay.restapi.dao;

import com.scratchpay.restapi.UserEntity.User;

import java.util.List;

public interface UserDao {

    int save(User user);

    List<User> getUserList();

    User getUser(int id);
}
