package com.dhriti.wehealth.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.dhriti.wehealth.WeHealthApplication;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterFIreBaseTokenViewModel extends ViewModel {

    private OkHttpClient mOkHttpClient;
    private Request mRequest;
    private String mMsg;

    public RegisterFIreBaseTokenViewModel(OkHttpClient okHttpClient, Request request){
        this.mOkHttpClient = okHttpClient;
        this.mRequest = request;
    }

    public void registerFireBaseToken(){
        Gson gson = new Gson();
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {
                Log.v("Firebase", response.body().toString());
            }
        });
    }

}
