package com.halove.latte.net.download;

import android.os.AsyncTask;

import com.halove.latte.net.RestCreator;
import com.halove.latte.net.callback.IError;
import com.halove.latte.net.callback.IFailure;
import com.halove.latte.net.callback.IRequest;
import com.halove.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xieshangwu on 2017/7/15 0015
 */

public class DownloadHandler {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    public DownloadHandler(String url, IRequest request, String download_dir, String extension,
                           String name, ISuccess success, IError error, IFailure failure) {
        URL = url;
        REQUEST = request;
        DOWNLOAD_DIR = download_dir;
        EXTENSION = extension;
        NAME = name;
        SUCCESS = success;
        ERROR = error;
        FAILURE = failure;
    }

    public final void handleDownload() {
        if(REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    final SaveFileTask saveFileTask = new SaveFileTask(REQUEST, SUCCESS);
                    saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR,
                            EXTENSION, response, NAME);

                    //这里一定要注意判断， 否则文件下载不全
                    if(saveFileTask.isCancelled()) {
                        if(REQUEST != null) {
                            REQUEST.onRequestEnd();
                        }
                    }
                } else {
                    if(ERROR != null) {
                        ERROR.onError(response.code(), response.message());

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(FAILURE != null) {
                    FAILURE.onFailure();
                }
            }
        });
    }
}
