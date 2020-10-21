package com.lkl.controller.common;

import com.lkl.entity.User;
import com.lkl.pojo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2020/10/14
 * @author 刘奎亮
 */
@Slf4j
@Api(tags = "用户公共相关接口")
@RestController
@RequestMapping("/public")
public class UserController {

    @ApiOperation("用户登录")
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
            log.error("username error--------{}",    e);
            result.setSuccess(false);
            result.setMessage("用户名错误");
        }catch (IncorrectCredentialsException e){
            log.error("password error--------{}",e);
            result.setSuccess(false);
            result.setMessage("密码错误");
        }
        return result;
    }

    @ApiOperation("用户注销")
    @RequestMapping(value = "/user/logout",method = {RequestMethod.POST,RequestMethod.GET})
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
