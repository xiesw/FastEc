package com.halove.fastec;

import android.app.Application;

import com.halove.latte.app.Latte;

/**
 * Created by xieshangwu on 2017/7/13
 */

public class EcApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).withApiHost("http:192.168.1.105").configure();
    }
}
