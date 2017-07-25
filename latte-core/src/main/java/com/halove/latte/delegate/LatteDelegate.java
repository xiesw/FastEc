package com.halove.latte.delegate;

/**
 * Created by xieshangwu on 2017/7/13 0013
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {

    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
