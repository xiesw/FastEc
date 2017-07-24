package com.halove.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.halove.latte.delegate.LatteDelegate;

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
    }
}
