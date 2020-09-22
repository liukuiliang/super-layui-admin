package com.lkl.mapper;

import com.lkl.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

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
