package com.halove.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.halove.latte.app.Latte;

/**
 * Created by xieshangwu on 2017/7/14 0014
 */

public class DimenUtil {
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
