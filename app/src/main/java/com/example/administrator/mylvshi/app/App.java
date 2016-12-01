package com.example.administrator.mylvshi.app;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/11/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
