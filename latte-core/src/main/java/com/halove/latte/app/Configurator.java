package com.halove.latte.app;

import java.util.WeakHashMap;

/**
 * Created by xieshangwu on 2017/7/13
 */

public class Configurator {
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
    }
}
