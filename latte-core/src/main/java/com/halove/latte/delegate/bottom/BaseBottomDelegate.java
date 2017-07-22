package com.halove.latte.delegate.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.halove.latte.R;
import com.halove.latte.R2;
import com.halove.latte.delegate.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xieshangwu on 2017/7/21 0021
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar;

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
            LayoutInflater.from(getContext())
                    .inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);
            // 初始化数据
            itemIcon.setText(bean.getICON());
            itemTitle.setText(bean.getTITLE());
            if(i == mIndexDelegate) {
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }

        final SupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_deleage_container, mIndexDelegate, delegateArray);

    }

    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for(int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemText = (AppCompatTextView) item.getChildAt(1);
            itemIcon.setTextColor(Color.GRAY);
            itemText.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View view) {
        final int tag = (int) view.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) view;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        final AppCompatTextView itemText = (AppCompatTextView) item.getChildAt(1);
        itemIcon.setTextColor(mClickedColor);
        itemText.setTextColor(mClickedColor);
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get
                (mCurrentDelegate));
        // 注意先后顺序
        mCurrentDelegate = tag;

    }
}
