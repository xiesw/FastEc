package com.halove.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.halove.latte.net.RestClient;
import com.halove.latte.net.callback.ISuccess;
import com.halove.latte.ui.recycler.DataConverter;
import com.halove.latte.ui.recycler.MultipleRecyclerAdapter;

/**
 * Created by xieshangwu on 2017/7/22
 */

public class Refreshhandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter
        .RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter;
    private final DataConverter CONVERTER;


    private Refreshhandler(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView,
                           DataConverter converter, PagingBean bean) {
        REFRESH_LAYOUT = swipeRefreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
        RECYCLERVIEW = recyclerView;
        CONVERTER = converter;
        BEAN = bean;
    }

    public static Refreshhandler create(SwipeRefreshLayout swipeRefreshLayout, RecyclerView
            recyclerView, DataConverter converter, PagingBean bean) {
        return new Refreshhandler(swipeRefreshLayout, recyclerView, converter, bean);
    }


    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
    }

    public void firstPage(String url) {

        BEAN.setDelay(1000);
        RestClient.builder().url(url).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                final JSONObject object = JSON.parseObject(response);
                BEAN.setTotal(object.getInteger("total"))
                        .setPageSize(object.getInteger("page_size"));

                mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                mAdapter.setOnLoadMoreListener(Refreshhandler.this, RECYCLERVIEW);
                RECYCLERVIEW.setAdapter(mAdapter);
                BEAN.addIndex();
            }
        }).build().get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
