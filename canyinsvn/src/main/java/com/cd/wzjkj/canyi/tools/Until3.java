package com.cd.wzjkj.canyi.tools;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 44967 on 2017/3/20.
 */
public class Until3 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
