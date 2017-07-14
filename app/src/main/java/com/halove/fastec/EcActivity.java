package com.halove.fastec;

import com.halove.latte.activities.ProxyActivity;
import com.halove.latte.delegate.LatteDelegate;

public class EcActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new EcDelegate();
    }

}
