package com.ld.lib_aop.singleclick;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;

import com.ld.lib_aop.singleclick.annotation.SingleClick;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * author : ld
 * time   : 2021/01/14
 * desc   :
 */
@Aspect
public class SingleClickAspect {

    public static final String Tag = "SingleClickAspect";

    private static long mLastClickTime;

    private static final String POINTCUT_METHOD = "execution(@com.ld.lib_aop.singleclick.annotation.SingleClick * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodPointcut() {

    }

    @Around("methodPointcut()")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        Log.i(Tag, "check click");
        try {
            //查找方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            //检查是否有注解
            boolean hasAnnotation = method != null && method.isAnnotationPresent(SingleClick.class);
            //计算点击间隔，没有注解默认500，有注解按注解参数来，注解参数为空默认500；
            int interval = 500;
            if (hasAnnotation) {
                SingleClick annotation = method.getAnnotation(SingleClick.class);
                interval = annotation.value();
            }
            //获取被点击的View对象
            Object[] args = joinPoint.getArgs();
            View view = findViewInMethodArgs(args);

            if (view != null) {
                int id = view.getId();
                //注解排除某个控件不防止双击
                if (hasAnnotation) {
                    SingleClick annotation = method.getAnnotation(SingleClick.class);
                    //按id值排除不防止双击的按钮点击
                    int[] except = annotation.except();
                    for (int i : except) {
                        if (i == id) {
                            mLastClickTime = System.currentTimeMillis();
                            joinPoint.proceed();
                            Log.i(Tag, "快速点击-1");
                            return;
                        }
                    }

                    //按id名排除不防止双击的按钮点击（非app模块）
                    String[] idName = annotation.exceptIdName();
                    Resources resources = view.getResources();
                    for (String name : idName) {
                        int resId = resources.getIdentifier(name, "id", view.getContext().getPackageName());
                        if (resId == id) {
                            mLastClickTime = System.currentTimeMillis();
                            joinPoint.proceed();
                            Log.i(Tag, "快速点击-2");
                            return;
                        }
                    }
                }

                if (canClick(interval)) {
                    mLastClickTime = System.currentTimeMillis();
                    joinPoint.proceed();
                    Log.i(Tag, "快速点击-3");
                    return;
                }
            }

            //检测间隔时间是否达到预设时间并且线程空闲
            if (canClick(interval)) {
                mLastClickTime = System.currentTimeMillis();
                joinPoint.proceed();
                Log.i(Tag, "check click can continue");
            } else {
                Log.i(Tag, "check click can not continue");
            }
        } catch (Exception e) {
            //出现异常 不拦截点击事件
            Log.i(Tag, "出现异常,继续执行-5");
            joinPoint.proceed();
        }
    }

    public View findViewInMethodArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof View) {
                View view = (View) args[i];
                if (view.getId() != View.NO_ID) {
                    return view;
                }
            }
        }
        return null;
    }

    public boolean canClick(int interval) {
        long l = System.currentTimeMillis() - mLastClickTime;
        if (l > interval) {
            mLastClickTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
