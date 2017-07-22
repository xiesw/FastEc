package com.halove.latte.ec.main;

import android.graphics.Color;

import com.halove.latte.delegate.bottom.BaseBottomDelegate;
import com.halove.latte.delegate.bottom.BottomItemDelegate;
import com.halove.latte.delegate.bottom.BottomTabBean;
import com.halove.latte.delegate.bottom.ItemBuilder;
import com.halove.latte.ec.main.index.IndexDelegate;
import com.halove.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by xieshangwu on 2017/7/22
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
