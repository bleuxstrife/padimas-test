package com.interview.test.padimas_test.api;

import android.content.Context;

import com.interview.test.padimas_test.utils.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseConnection {

    private Context context;
    private static Retrofit auth = null;

    private static OkHttpClient okHttpClient;
    private OnRequestResultListener resultListener;

    public BaseConnection(Context context) {
        this.context = context;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
    }

    public static Retrofit getAPI() {
        if (auth == null) {
            auth = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return auth;
    }





    protected void sendResponse(Object response) {
        sendResponse(null, response, null, -1);
    }

    protected void sendResponse(Object response, int type) {
        sendResponse(null, response, null, type);
    }

    protected void sendResponse(Object response, String id, int responeCode) {
        sendResponse(null, response, id, responeCode);
    }

    protected void sendResponse(Throwable t) {
        sendResponse(t, null, null, -1);
    }

    private void sendResponse(Throwable t, Object response, String id, int type) {
        if (getResultListener() != null) {
            if (id != null) {
                getResultListener().onRequestResultSuccessWithId(response, id, type);
            } else if (response != null) {
                getResultListener().onRequestResultSuccessWithType(response, type);
            } else if (t != null) {
                getResultListener().onRequestResultError(t);
            }
        }
    }

    public void setOnRequestResultListener(OnRequestResultListener resultListener) {
        this.resultListener = resultListener;
    }

    private OnRequestResultListener getResultListener() {
        return resultListener;
    }


    public interface OnRequestResultListener {

        public void onRequestResultSuccessWithType(Object res, int type);

        public void onRequestResultError(Throwable throwable);

        public void onRequestResultSuccessWithId(Object res, String id, int type);

    }
}

