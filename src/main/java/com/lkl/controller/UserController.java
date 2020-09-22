package com.lkl.controller;

import com.lkl.entity.User;
import com.lkl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "findAll")
    public List<User> findAll(){

        return userService.findAll();
    }

    @RequestMapping(value = "login",method = {RequestMethod.GET,RequestMethod.POST})
    public User login(User user){
        return userService.login(user);
    }
}
