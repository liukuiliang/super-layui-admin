package com.lkl.mapper;

import com.lkl.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface UserMapper {

    User findByUserName(@Param("username") String username);
}
