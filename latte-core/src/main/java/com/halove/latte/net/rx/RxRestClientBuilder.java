package com.halove.latte.net.rx;

import android.content.Context;

import com.halove.latte.net.RestClient;
import com.halove.latte.net.RestCreator;
import com.halove.latte.net.callback.IError;
import com.halove.latte.net.callback.IFailure;
import com.halove.latte.net.callback.IRequest;
import com.halove.latte.net.callback.ISuccess;
import com.halove.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by xieshangwu on 2017/7/14 0014
 */

public class RxRestClientBuilder {
    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mRequest;
    private String mDownloadDir;
    private String mName;
    private String mExtension;
    private ISuccess mSuccess;
    private IError mError;
    private IFailure mFailure;
    private RequestBody mBody;
    private File mFile;
    private Context mContext;
    private LoaderStyle mLoaderStyle;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(Map<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClientBuilder onRequest(IRequest request) {
        this.mRequest = request;
        return this;
    }

    public final RxRestClientBuilder dir(String downloadDir) {
        this.mDownloadDir = downloadDir;
        return this;
    }

    public final RxRestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RxRestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RxRestClientBuilder success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    public final RxRestClientBuilder failure(IFailure iFailure) {
        this.mFailure = iFailure;
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    public final RxRestClientBuilder error(IError iError) {
        this.mError = iError;
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mRequest, mDownloadDir, mExtension, mName, mSuccess,
                mError, mFailure, mBody, mFile, mLoaderStyle, mContext);
    }
}
