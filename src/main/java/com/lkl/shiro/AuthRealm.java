package com.lkl.shiro;

import com.lkl.entity.Permission;
import com.lkl.entity.Role;
import com.lkl.entity.User;
import com.lkl.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    // 认证授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        User byUserName = userService.findByUserName(user.getUsername());
        List<String> permissionList = new ArrayList<>();
        if(ObjectUtils.isNotEmpty(byUserName)){
            for (Role role : byUserName.getRoles()){
                Set<Permission> permissions = role.getPermissions();
                if(ObjectUtils.isNotEmpty(permissions)){
                    for(Permission permission : permissions) {
                        permissionList.add(permission.getPname());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        return info;
    }

    // 认证登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        User user = userService.findByUserName(username);
        if(user == null){
            return null;
        }
//        if(!bCryptPasswordEncoder.matches(password,user.getPassword())){
//           return null;
//        }
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(
                        user,user.getPassword(),getName()
                );
        return simpleAuthenticationInfo;
    }
}
