package com.dhriti.wehealth;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.dhriti.wehealth.listeners.WeHealthMQTTSubscriber;

public class WeHealthApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static void displayToast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
