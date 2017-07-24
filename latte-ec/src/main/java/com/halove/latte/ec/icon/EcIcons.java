package com.halove.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by xieshangwu on 2017/7/13 0013
 */

public enum EcIcons implements Icon{
    icon_scan('\ue602'),
    icon_ali_pay('\ue606');
    private char character;

    EcIcons(char cjaracter) {
        this.character = cjaracter;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
