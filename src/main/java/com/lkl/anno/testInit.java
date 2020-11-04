package com.lkl.anno;

import java.lang.annotation.*;

/**
 * @author 刘奎亮
 * @date 2020/10/22
 */
@Documented
@Inherited
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface testInit {

    public String value() default "";

}
