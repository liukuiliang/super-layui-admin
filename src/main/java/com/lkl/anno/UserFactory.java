package com.lkl.anno;

import com.lkl.entity.User;

import java.lang.reflect.Method;

/**
 * @author 刘奎亮
 * @date 2020/10/22
 */
public class UserFactory {
    public static User create(){
        User user = new User();
        Method[] methods1 = User.class.getMethods();
                try {
                    for (Method method:methods1) {
                        if (method.isAnnotationPresent(testInit.class)) {
                            testInit testInit = method.getAnnotation(testInit.class);
                            method.invoke(user, testInit.value());
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
        return user;
    }
}
