package com.dhriti.wehealth.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.dhriti.wehealth.model.HealthVitalsResponseVO;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HealthVitalsListViewModel extends ViewModel {

    private OkHttpClient mOkHttpClient;
    private Request mRequest;
    private HealthVitalsResponseVO mHealthVitalsResponseVO;
    private MutableLiveData<HealthVitalsResponseVO> mHealthVitalsResponseVOMutableLiveData;

    public HealthVitalsListViewModel(OkHttpClient okHttpClient, Request request) {
        this.mOkHttpClient = okHttpClient;
        this.mRequest = request;
        mHealthVitalsResponseVOMutableLiveData = new MutableLiveData<>();
    }

    public void getHealthVitals() {
        Gson gson = new Gson();
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mHealthVitalsResponseVO =
                        gson.fromJson(response.body().string(), HealthVitalsResponseVO.class);

                mHealthVitalsResponseVOMutableLiveData.postValue(mHealthVitalsResponseVO);
            }
        });

    }


    public LiveData<HealthVitalsResponseVO> loadHealthVitals() {
        return mHealthVitalsResponseVOMutableLiveData;
    }
}
