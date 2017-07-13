package com.halove.fastec;

import android.app.Application;

import com.halove.latte.app.Latte;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by xieshangwu on 2017/7/13
 */

public class EcApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withApiHost("http:192.168.1.105")
                .configure();
    }
}
