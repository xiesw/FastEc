package com.halove.latte.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.halove.latte.ec.R;
import com.halove.latte.ui.recycler.RgbValue;

/**
 * Created by xieshangwu on 2017/7/24
 */

public class TransluncentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    // 顶部距离
    private int mDistanceY = 0;
    // 颜色变化速率
    private static final int SHOW_SPEED = 3;
    //定义变化的颜色
    private final RgbValue RGBVALUE = RgbValue.create(255, 124, 2);

    public TransluncentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View
            directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View
            target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        // 增加滑动距离
        mDistanceY += dy;
        // toolbar高度
        final int targetHeight = child.getBottom();

        // 当滑动时， 并且距离小于toolbar高度的时候， 调整渐变色
        if(mDistanceY > 0 && mDistanceY <= targetHeight) {
            final float scale = (float) mDistanceY / targetHeight;
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb((int) alpha, RGBVALUE.red(), RGBVALUE.green(), RGBVALUE
                    .blue()));
        } else if(mDistanceY > targetHeight) {
            child.setBackgroundColor(Color.rgb(RGBVALUE.red(), RGBVALUE.green(), RGBVALUE
                    .blue()));
        }

    }
}
