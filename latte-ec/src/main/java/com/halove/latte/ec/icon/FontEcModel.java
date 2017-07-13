package com.halove.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by xieshangwu on 2017/7/13 0013
 */

public class FontEcModel implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfot.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
