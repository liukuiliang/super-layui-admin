package com.lkl.entity;

import com.lkl.anno.testInit;
import lombok.Data;

import java.util.Set;


//@Data
public class User {
    private int uid;
    private String username;
    private String password;
    private Set<Role> roles;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    @testInit(value = "刘奎亮")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
