package com.halove.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.halove.latte.delegate.LatteDelegate;
import com.halove.latte.net.RestClient;
import com.halove.latte.net.callback.IError;
import com.halove.latte.net.callback.IFailure;
import com.halove.latte.net.callback.ISuccess;

/**
 * Created by xieshangwu on 2017/7/14 0014
 */

public class EcDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_ec;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder().url("http://www.baidu.com/")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                }).build().get();
    }
}
