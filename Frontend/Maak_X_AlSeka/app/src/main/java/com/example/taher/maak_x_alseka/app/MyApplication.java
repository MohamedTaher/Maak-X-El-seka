package com.example.taher.maak_x_alseka.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by taher on 25/01/17.
 */
public class MyApplication extends Application {

    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
