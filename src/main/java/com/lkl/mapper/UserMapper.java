package com.lkl.mapper;

import com.lkl.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    User findByUserName(String username);
}
