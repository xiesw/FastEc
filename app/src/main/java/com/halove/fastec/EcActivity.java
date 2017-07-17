package com.halove.fastec;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.halove.latte.activities.ProxyActivity;
import com.halove.latte.delegate.LatteDelegate;
import com.halove.latte.ec.launcher.LauncherDelegate;
import com.halove.latte.ec.sign.ISignListener;
import com.halove.latte.ec.sign.SignInDelegate;
import com.halove.latte.ui.launcher.ILauncherListener;
import com.halove.latte.ui.launcher.OnLauncherFinishTag;

public class EcActivity extends ProxyActivity implements ISignListener,ILauncherListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle
            persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch(tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束,用户登陆", Toast.LENGTH_SHORT).show();
                // todo 进入主页
                startWithPop(new EcDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束,用户没登陆", Toast.LENGTH_SHORT).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
