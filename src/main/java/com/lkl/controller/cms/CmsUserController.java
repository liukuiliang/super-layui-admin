package com.lkl.controller.cms;

import com.alibaba.fastjson.JSONObject;
import com.lkl.entity.User;
import com.lkl.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘奎亮
 * @date 2020/10/22
 */
@Slf4j
@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/cms")
public class CmsUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "test",method = {RequestMethod.POST,RequestMethod.GET})
    public String test(String username){
        User byUserName = userService.findByUserName(username);
        return JSONObject.toJSONString(byUserName);
    }
}
