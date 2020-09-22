package com.lkl.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private int id; // 编号
    private String userName; // 用户名
    private String userGroups; // 用户组
    private String realName; // 真实姓名
    private int gender; // 性别
    private String telephone; // 手机号
    private Date birthday; // 出生日期
    private String address; // 地址
    private String skills; // 掌握技术
    private String email; // 邮箱
    private String evaluation; // 自我评价
    private int level; // 等级
    private int status; // 状态
    private String password; // 密码
    private int isDelete; // 是否删除  0-否  1-是
}
