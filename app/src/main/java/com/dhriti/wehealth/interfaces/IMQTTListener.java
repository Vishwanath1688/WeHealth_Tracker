package com.dhriti.wehealth.interfaces;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface IMQTTListener {

    void onMessageReceived(String topic, MqttMessage message);
}
