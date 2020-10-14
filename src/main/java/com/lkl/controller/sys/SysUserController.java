package com.lkl.controller.sys;

import com.lkl.entity.User;
import com.lkl.pojo.Result;
import com.lkl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 14/10/2020
 * @author 刘奎亮
 */
@Slf4j
@RestController
@RequestMapping("/sys")
public class SysUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/login",method = {RequestMethod.POST,RequestMethod.GET})
    public Result loginUser(User user){
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("登录成功");
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
            subject.login(token);
        }catch (UnknownAccountException e){
            log.error("用户名错误--------{}",    e);
            result.setSuccess(false);
            result.setMessage("用户名错误");
        }catch (IncorrectCredentialsException e){
            log.error("密码错误--------{}",e);
            result.setSuccess(false);
            result.setMessage("密码错误");
        }
        return result;
    }
}
