package com.ld.lib_common.provider;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

public class AppProvider {
    public static final String TAG = "AppProvider";
    @SuppressLint("StaticFieldLeak")
    private Application app;

    private static AppProvider INSTANCE;

    public AppProvider(Application app) {
        this.app = app;
    }

    public static void init(Application application) {
        if (INSTANCE == null) {
            Log.i(TAG, "init: AppProvider=contentprovider获取");
            INSTANCE = new AppProvider(application);
        }
    }

    public static AppProvider getInstance() {
        if (INSTANCE == null) {
            Log.i(TAG, "init: AppProvider=反射获取");
            INSTANCE = new AppProvider(AppBridge.getApplicationByReflect());
        }
        return INSTANCE;
    }

    public Application getApp() {
        return app;
    }
}
