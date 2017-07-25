package com.halove.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.halove.latte.delegate.LatteDelegate;
import com.halove.latte.ec.detail.GoodsDetailDelegate;

/**
 * Created by xieshangwu on 2017/7/25
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    public IndexItemClickListener(LatteDelegate dalegate) {
        DELEGATE = dalegate;
    }

    public static SimpleClickListener create(LatteDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetailDelegate detailDelegate = GoodsDetailDelegate.create();
        DELEGATE.start(detailDelegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
