package com.halove.latte.ui.refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

import com.halove.latte.net.RestClient;
import com.halove.latte.net.callback.IError;
import com.halove.latte.net.callback.IFailure;
import com.halove.latte.net.callback.ISuccess;

/**
 * Created by xieshangwu on 2017/7/22
 */

public class Refreshhandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public Refreshhandler(SwipeRefreshLayout refresh_layout) {
        REFRESH_LAYOUT = refresh_layout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }


    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 进行网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);

    }

    public void firstPage(String url) {


    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
