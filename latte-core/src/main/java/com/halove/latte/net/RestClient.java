package com.halove.latte.net;

import android.content.Context;

import com.halove.latte.net.callback.IError;
import com.halove.latte.net.callback.IFailure;
import com.halove.latte.net.callback.IRequest;
import com.halove.latte.net.callback.ISuccess;
import com.halove.latte.net.callback.RequestCallbacks;
import com.halove.latte.ui.LatteLoader;
import com.halove.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by xieshangwu on 2017/7/14 0014
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest equest, ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context) {
        URL = url;
        PARAMS.putAll(params);
        REQUEST = equest;
        SUCCESS = success;
        ERROR = error;
        FAILURE = failure;
        BODY = body;
        LOADER_STYLE = loaderStyle;
        CONTEXT = context;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    public void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if(REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if(LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch(method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if(call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, ERROR, FAILURE, LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }
    public final void gost() {
        request(HttpMethod.POST);
    }
    public final void put() {
        request(HttpMethod.PUT);
    }
    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
