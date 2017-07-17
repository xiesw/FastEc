package com.halove.latte.app;

import com.halove.latte.util.LattePreference;

/**
 * Created by xieshangwu on 2017/7/17 0017
 */

public class AccountManager {
    private enum SignTag {
        SIGN_TAG
    }

    // 保存用户登录状态, 登录后调用
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IuserChecker checker) {
        if(isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }

    }
}
