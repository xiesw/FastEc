package com.halove.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.halove.latte.delegate.bottom.BottomItemDelegate;
import com.halove.latte.ec.R;

/**
 * Created by xieshangwu on 2017/7/22
 */

public class IndexDelegate extends BottomItemDelegate
{
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
