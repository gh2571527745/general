package com.ld.lib_aop.singleclick.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : ld
 * time   : 2021/01/14
 * desc   :
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleClick {
    int value() default 2000;

    int[] except() default {};

    String[] exceptIdName() default {};
}
