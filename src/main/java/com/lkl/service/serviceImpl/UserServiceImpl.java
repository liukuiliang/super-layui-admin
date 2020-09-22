package com.lkl.service.serviceImpl;

import com.lkl.entity.User;
import com.lkl.mapper.UserMapper;
import com.lkl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
