package com.he.dao;

import com.he.pojo.User;

public interface UserDao {
    public User findByUsername(String username);
}
