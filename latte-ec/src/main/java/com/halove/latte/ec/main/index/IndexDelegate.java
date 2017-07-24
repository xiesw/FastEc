package com.halove.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.halove.latte.delegate.bottom.BottomItemDelegate;
import com.halove.latte.ec.R;
import com.halove.latte.ec.R2;
import com.halove.latte.ui.refresh.PagingBean;
import com.halove.latte.ui.refresh.Refreshhandler;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;

/**
 * Created by xieshangwu on 2017/7/22
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView;

    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R2.id.tv_index)
    Toolbar mToolbar;

    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan;

    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView;

    private Refreshhandler mRefreshhandler;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshhandler = Refreshhandler.create(mSwipeRefreshLayout, mRecyclerView, new
                IndexDataConverter(), new PagingBean());
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R
                .color.holo_red_light, android.R.color.holo_orange_light);
        mSwipeRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshhandler.firstPage("index_data.json");
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
