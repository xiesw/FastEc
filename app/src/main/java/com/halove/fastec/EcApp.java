package com.halove.fastec;

import android.app.Application;

import com.halove.latte.app.Latte;
import com.halove.latte.ec.icon.FontEcModel;
import com.halove.latte.net.Interceptors.DebugInterceptor;
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
                .withIcon(new FontEcModel())
                .withLoadDelay(3000)
                .withApiHost("http://192.168.1.105/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }
}
