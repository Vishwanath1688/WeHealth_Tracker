package com.dhriti.wehealth.services;

import android.util.Log;

import com.dhriti.wehealth.WeHealthApplication;
import com.dhriti.wehealth.network.WeHealthServiceClient;
import com.dhriti.wehealth.utils.WeHealthConstants;
import com.dhriti.wehealth.utils.WeHealthUtils;
import com.dhriti.wehealth.viewmodel.RegisterFIreBaseTokenViewModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class WeHealthFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "WeHealthFirebaseMessagingService";
    private RegisterFIreBaseTokenViewModel mRegisterFIreBaseTokenViewModel;

    public WeHealthFirebaseMessagingService() {
        super();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        WeHealthUtils.sendNotification(remoteMessage.getNotification().getBody(),
                WeHealthApplication.getAppContext());
        super.onMessageReceived(remoteMessage);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
    }

    @Override
    public void onNewToken(String token) {
        Log.v(TAG, "Token : " + token);
        RequestBody mBody = new FormBody.Builder()
                .add("token", token).build();
        mRegisterFIreBaseTokenViewModel = new RegisterFIreBaseTokenViewModel(
                WeHealthServiceClient.getInstance().getClient(),
                WeHealthServiceClient.getInstance().getPostRequest
                        (WeHealthConstants.WE_HEALTH_REGISTER_FIREBASE_TOKEN, mBody));
        mRegisterFIreBaseTokenViewModel.registerFireBaseToken();
        super.onNewToken(token);
    }
}
