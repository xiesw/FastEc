package com.halove.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;

import com.halove.latte.delegate.LatteDelegate;
import com.halove.latte.ec.R;
import com.halove.latte.ec.R2;
import com.halove.latte.net.RestClient;
import com.halove.latte.net.callback.IError;
import com.halove.latte.net.callback.IFailure;
import com.halove.latte.net.callback.ISuccess;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xieshangwu on 2017/7/16 0016
 */

public class SignInDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword;
    @BindView(R2.id.btn_sing_in)
    AppCompatButton mBtnSingIn;
    @BindView(R2.id.tv_link_sign_up)
    AppCompatTextView mTvLinkSignIn;
    @BindView(R2.id.icon_sign_in_wechat)
    IconTextView mIconSignInWechat;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sing_in)
    void onClickSignIn() {
        if(checkForm()) {
            RestClient.builder()
                    .url("user_profile.json")
                    .params("email",mEmail.getText().toString())
                    .params("password",mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {

    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        start(new SignUpDelegate(), SINGLETASK);
    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if(password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
