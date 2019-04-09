package com.interview.test.padimas_test.utils.base;



import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;


import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class BaseApplication extends Application {
    public final static String TAG = "BaseApplication";
    public final static boolean DEBUG = true;
    private static BaseApplication application;
    private static int mainTid;
    private static List<BaseActivity> activities;
    private static String sCacheDir;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        activities = new LinkedList<>();
        application = this;
        mainTid = android.os.Process.myTid();
        if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            sCacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            sCacheDir = getApplicationContext().getCacheDir().toString();
        }
    }


    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static String getAppCacheDir() {
        return sCacheDir;
    }

    public static Context getApplication() {
        return application;
    }

    public static int getMainTid() {
        return mainTid;
    }

    public void addActivity(BaseActivity activity) {
        activities.add(activity);
    }

    public void removeActivity(BaseActivity activity) {
        activities.remove(activity);
    }

    public static void clearActivities() {
        ListIterator<BaseActivity> iterator = activities.listIterator();
        BaseActivity activity;
        while (iterator.hasNext()) {
            activity = iterator.next();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    public static void quiteApplication() {
        clearActivities();
        System.exit(0);
    }
}
