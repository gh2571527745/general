package com.ld.lib_aop.checklogin;

import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ld.lib_aop.checklogin.annotation.CheckLogin;
import com.ld.lib_base.arouter.RouterActivityPath;
import com.ld.lib_base.bean.login.UserInfoBean;
import com.ld.lib_base.util.CacheUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * author : ld
 * time   : 2021/01/13
 * desc   :检查是否登录
 */
@Aspect
public class CheckLoginAspect {

    public static final String Tag = "CheckLoginAspect";

    @Pointcut("execution(" +//执行语句
            "@com.ld.lib_aop.checklogin.annotation.CheckLogin" +//注解筛选
            " * " + //类路径,*为任意路径
            "*" +   //方法名,*为任意方法名
            "(..)" +//方法参数,'..'为任意个任意类型参数
            ")" +
            " && " +//并集
            "@annotation(checkLogin)"//注解筛选,这里主要用于下面方法的'checkLogin'参数获取
    )
    public void pointcutCheckLogin(CheckLogin checkLogin) {

    }

    @Around("pointcutCheckLogin(checkLogin)")
    public Object aroundCheckLogin(ProceedingJoinPoint joinPoint, final CheckLogin checkLogin) throws Throwable {
        Log.i(Tag, "check login");
        UserInfoBean user = CacheUtil.INSTANCE.getUser();
        if (user == null) {
            Log.i(Tag, "login fail");
            ARouter.getInstance().build(RouterActivityPath.Login.PAGER_LOGIN).navigation();
            return null;
        } else {
            Log.i(Tag, "login success");
            return joinPoint.proceed();
        }
    }
}
