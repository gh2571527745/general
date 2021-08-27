package com.ld.lib_common.provider;

import android.app.Application;

public class AppBridge {

    public static Application getApplicationByReflect() {
        return ActivityLifecycleImpl.INSTANCE.getApplicationByReflect();
    }
}
