package com.halove.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by xieshangwu on 2017/7/16 0016
 */

public class LauncherHolderCreator implements CBViewHolderCreator<Launcherhollder> {


    @Override
    public Launcherhollder createHolder() {
        return new Launcherhollder();
    }
}
