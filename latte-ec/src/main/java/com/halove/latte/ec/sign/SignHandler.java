package com.halove.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.halove.latte.app.AccountManager;
import com.halove.latte.ec.database.DatabaseManager;
import com.halove.latte.ec.database.UserProfile;

/**
 * Created by xieshangwu on 2017/7/17 0017
 */

public class SignHandler {

    public static void onSignIn(String response, ISignListener iSignListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        // TODO: 2017/7/17 0017 bug 1
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经登录成功了
        AccountManager.setSignState(true);
        iSignListener.onSignInSuccess();
    }

    public static void onSignUp(String response, ISignListener iSignListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        // TODO: 2017/7/17 0017 bug 1
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        iSignListener.onSignUpSuccess();
    }
}
