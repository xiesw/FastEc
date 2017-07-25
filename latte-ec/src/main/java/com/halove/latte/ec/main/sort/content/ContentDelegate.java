package com.halove.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.halove.latte.delegate.LatteDelegate;
import com.halove.latte.ec.R;
import com.halove.latte.ec.R2;
import com.halove.latte.net.RestClient;
import com.halove.latte.net.callback.ISuccess;

import butterknife.BindView;

/**
 * Created by xieshangwu on 2017/7/25
 */

public class ContentDelegate extends LatteDelegate {


    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView;

    private static String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentID = -1;
    public static ContentDelegate newInstance(int contentID) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentID);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if(args != null) {
            mContentID = args.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    private void initData() {
        RestClient
                .builder()
                .url("")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .build()
                .get();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }
}
