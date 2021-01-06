package com.he.service;

import com.he.pojo.User;

public interface UserService {
    public User findByUsername(String username);
}
