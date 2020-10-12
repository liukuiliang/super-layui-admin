package com.lkl.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
public class User {
    private int id;
    private String username;
    private String password;
    Set<Role> roleSet = new HashSet<>();
    Set<Permission> permissionSet = new HashSet<>();
}
