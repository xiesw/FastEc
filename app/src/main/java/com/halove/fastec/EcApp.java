package com.halove.fastec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.halove.latte.app.Latte;
import com.halove.latte.ec.database.DatabaseManager;
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
                .withApiHost("http://121.42.181.106:8080/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);

        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
