package com.halove.latte.delegate.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.View;

import com.halove.latte.R;
import com.halove.latte.delegate.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xieshangwu on 2017/7/21 0021
 */

public abstract class BaseBottomDelegate extends LatteDelegate{
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if(setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);

        ITEMS.putAll(items);

        for(Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for(int i = 0; i < size; i++) {
            //LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout,)
        }
    }
}
