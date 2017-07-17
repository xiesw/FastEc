package com.halove.fastec;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.halove.latte.activities.ProxyActivity;
import com.halove.latte.delegate.LatteDelegate;
import com.halove.latte.ec.sign.SignUpDelegate;

public class EcActivity extends ProxyActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle
            persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        /*RestClient.builder()
                .url("examples/data/about.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(EcActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(EcActivity.this, "failure", Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(EcActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                })
                .loader(this)
                .build()
                .get();*/
        return new SignUpDelegate();
    }

}
