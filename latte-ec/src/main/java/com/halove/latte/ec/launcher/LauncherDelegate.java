package com.halove.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.halove.latte.delegate.LatteDelegate;
import com.halove.latte.ec.R;
import com.halove.latte.ec.R2;
import com.halove.latte.util.LattePreference;
import com.halove.latte.util.timer.BaseTimerTask;
import com.halove.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xieshangwu on 2017/7/15 0015
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener{

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private int mCount = 5;
    private Timer mTimer;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if(mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);
    }

    @Override
    public Object setLayout() {

        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    // 判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if(!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FRIST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            // todo 检查用户是否登陆APP
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if(mCount < 0) {
                        if(mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });

    }
}
