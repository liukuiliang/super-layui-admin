package com.lkl.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Role {
    private int rid;
    private String name;
    Set<User> userSet = new HashSet<>();
    Set<Permission> permissionSet = new HashSet<>();
}
