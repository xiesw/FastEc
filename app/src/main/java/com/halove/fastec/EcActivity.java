package com.halove.fastec;

import com.halove.latte.activities.ProxyActivity;
import com.halove.latte.delegate.LatteDelegate;
import com.halove.latte.ec.launcher.LauncherScrollDelegate;

public class EcActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }

}
