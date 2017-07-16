package com.halove.fastec;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.halove.latte.activities.ProxyActivity;
import com.halove.latte.delegate.LatteDelegate;
import com.halove.latte.ec.launcher.LauncherDelegate;

public class EcActivity extends ProxyActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle
            persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

}
