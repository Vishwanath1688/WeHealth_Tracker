package com.dhriti.wehealth.listeners;

import android.content.Context;
import android.util.Log;

import com.dhriti.wehealth.interfaces.IMQTTListener;
import com.dhriti.wehealth.utils.WeHealthConstants;
import com.dhriti.wehealth.utils.WeHealthUtils;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class WeHealthMQTTSubscriber implements MqttCallbackExtended, IMqttActionListener {

    private MqttAndroidClient mMqttAndroidClient;
    private static final String TAG = "WeHealthMQTTSubscriber";
    private IMQTTListener mImqttListener;

    public WeHealthMQTTSubscriber(Context context, IMQTTListener imqttListener){
        this.mImqttListener = imqttListener;
        mMqttAndroidClient = new MqttAndroidClient(context,
                WeHealthConstants.MQTT_SERVER_URI,
                WeHealthConstants.MQTT_CLIENT_ID);
        mMqttAndroidClient.setCallback(this);
        connectToMQTTServer();
    }

    private void connectToMQTTServer(){
        MqttConnectOptions mMqttConnectOptions = new MqttConnectOptions();
        mMqttConnectOptions.setAutomaticReconnect(true);
        mMqttConnectOptions.setCleanSession(false);
        mMqttConnectOptions.setUserName(WeHealthConstants.MQTT_USERNAME);
        mMqttConnectOptions.setPassword(WeHealthConstants.MQTT_PASSWORD.toCharArray());
        try {
            mMqttAndroidClient.connect(mMqttConnectOptions, null, this);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void subscribeToMQTTTopic(){
        try {
            mMqttAndroidClient.subscribe(WeHealthConstants.MQTT_SUBSCRIPTION_TOPIC, 0);
        } catch (MqttException ex) {
            Log.v(TAG, ex.getMessage());
        }
    }


    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        Log.v(TAG, serverURI);
    }

    @Override
    public void connectionLost(Throwable cause) {
        Log.v(TAG,cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Log.v(TAG, "Topic : " + topic + " ," +  " Message : " + message.toString());
        mImqttListener.onMessageReceived(topic, message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.v(TAG, token.toString());
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        Log.v(TAG, asyncActionToken.toString());
        DisconnectedBufferOptions mDisconnectedBufferOptions = new DisconnectedBufferOptions();
        mDisconnectedBufferOptions.setBufferEnabled(true);
        mDisconnectedBufferOptions.setBufferSize(WeHealthConstants.MQTT_BUFFER_SIZE);
        mDisconnectedBufferOptions.setPersistBuffer(false);
        mDisconnectedBufferOptions.setDeleteOldestMessages(false);
        mMqttAndroidClient.setBufferOpts(mDisconnectedBufferOptions);
        subscribeToMQTTTopic();
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        Log.v( TAG, "Failed to connect to: " + WeHealthConstants.MQTT_SERVER_URI +
                exception.toString());
    }

    public void unSubscribeMQTTConnection(){
        try {
            mMqttAndroidClient.unsubscribe(WeHealthConstants.MQTT_SUBSCRIPTION_TOPIC);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disConnectMQTTConnection(){
        mMqttAndroidClient.unregisterResources();
        try {
            mMqttAndroidClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
