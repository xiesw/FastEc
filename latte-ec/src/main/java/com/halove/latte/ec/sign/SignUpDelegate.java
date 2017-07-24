package com.halove.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.halove.latte.delegate.LatteDelegate;
import com.halove.latte.ec.R;
import com.halove.latte.ec.R2;
import com.halove.latte.net.RestClient;
import com.halove.latte.net.callback.IError;
import com.halove.latte.net.callback.IFailure;
import com.halove.latte.net.callback.ISuccess;
import com.halove.latte.util.LatteLogger;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

/**
 * Created by xieshangwu on 2017/7/16 0016
 */

public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mERePassword;
    @BindView(R2.id.btn_sing_up)
    AppCompatButton mBtnSingUp;
    @BindView(R2.id.tv_link_sign_in)
    AppCompatTextView mTvLinkSignIn;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sing_up)
    void onClickSignUp() {
        if(checkForm()) {
            RestClient.builder()
                    .url("user_profile.json")
                    .params("name",mName.getText().toString())
                    .params("email",mEmail.getText().toString())
                    .params("phone",mPhone.getText().toString())
                    .params("password",mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            SignHandler.onSignUp(response, mISignListener);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Log.d(TAG, "onFailure: ");
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Log.d(TAG, "onError: ");
                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        start(new SignInDelegate(), SINGLETASK);
    }

    private boolean checkForm() {

        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mERePassword.getText().toString();

        boolean isPass = true;

        if(name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if(phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if(password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if(rePassword.isEmpty() || rePassword.length() < 6) {
            mERePassword.setError("请填写至少6位数密码");
            isPass = false;
        } else if( !rePassword.equals(password)){
            mERePassword.setError("密码不一致");
            isPass = false;
        }else {
            mERePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
