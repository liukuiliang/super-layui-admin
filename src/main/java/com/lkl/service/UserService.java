package com.lkl.service;

import com.lkl.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 用户登录验证
     * @param user
     * @return
     */
    User login(User user);
    /**
     * 查询所有用户
     */
    List<User> findAll();
}
