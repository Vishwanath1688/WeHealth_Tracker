package com.dhriti.wehealth.network;

import com.dhriti.wehealth.utils.WeHealthConstants;

import java.util.logging.Level;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class WeHealthServiceClient {

    private OkHttpClient mOkHttpClient;
    private static WeHealthServiceClient mWeHealthServiceClient;

    private WeHealthServiceClient() {
    }

    public static WeHealthServiceClient getInstance() {
        if (mWeHealthServiceClient == null) {
            mWeHealthServiceClient = new WeHealthServiceClient();
        }
        return mWeHealthServiceClient;
    }

    public OkHttpClient getClient() {
        HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor();
        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(mHttpLoggingInterceptor)
                    .build();
        }

        return mOkHttpClient;
    }

    public static Request getRequest(String url) {
        Request mRequest = new Request.Builder()
                .url(WeHealthConstants.WE_HEALTH_DEV_URL + url)
                .build();
        return mRequest;
    }

    public static Request getPostRequest(String url, RequestBody requestBody){
        Request mRequest = new Request.Builder()
                .url(WeHealthConstants.WE_HEALTH_DEV_URL + url)
                .post(requestBody)
                .build();
        return mRequest;
    }
}



