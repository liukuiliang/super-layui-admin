package com.lkl.controller;

import com.lkl.entity.User;
import com.lkl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Slf4j
@Controller
public class userController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/add",method = {RequestMethod.POST,RequestMethod.GET})
    public String add(){
        return "/user/add";
    }

    @RequestMapping(value = "/del",method = {RequestMethod.POST,RequestMethod.GET})
    public String del(){
        return "user/del";
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/index",method = {RequestMethod.POST,RequestMethod.GET})
    public String index(){
        return "index";
    }

    @RequestMapping(value = "loginUser",method = {RequestMethod.POST,RequestMethod.GET})
    public String loginUser(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            // 登录成功
            subject.login(token);
            return "redirect:/index";
        }catch (Exception e){
            log.error("登录失败--------{}",e);
            return "redirect:/login";
        }
    }

}
