package com.lkl.entity;

import lombok.Data;

import java.util.Set;

@Data
public class Role {
    private int rid;
    private String rname;
    private Set<Permission> permissions;
}
