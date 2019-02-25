package com.dhriti.wehealth.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dhriti.wehealth.model.HealthVitalsResponseVO;
import com.dhriti.wehealth.model.VitalsSourceResponseVO;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VitalsSourcesListViewModel extends ViewModel {

    private OkHttpClient mOkHttpClient;
    private Request mRequest;
    private VitalsSourceResponseVO mVitalsSourceResponseVO;
    private MutableLiveData<VitalsSourceResponseVO> mVitalsSourceResponseVOMutableLiveData;

    public VitalsSourcesListViewModel(OkHttpClient okHttpClient, Request request) {
        this.mOkHttpClient = okHttpClient;
        this.mRequest = request;
        mVitalsSourceResponseVOMutableLiveData = new MutableLiveData<>();
    }

    public void getVitalsSource() {
        Response mResponse = null;
        Gson gson = new Gson();
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mVitalsSourceResponseVO =
                        gson.fromJson(response.body().string(), VitalsSourceResponseVO.class);
                mVitalsSourceResponseVOMutableLiveData.postValue(mVitalsSourceResponseVO);

            }
        });

    }


    public LiveData<VitalsSourceResponseVO> loadVitalsSource() {
        return mVitalsSourceResponseVOMutableLiveData;
    }
}
