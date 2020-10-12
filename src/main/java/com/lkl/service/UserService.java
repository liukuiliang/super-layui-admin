package com.lkl.service;

import com.lkl.entity.User;


public interface UserService {
    User findByUserName(String username);
}
