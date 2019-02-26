package com.dhriti.wehealth.utils;

public class WeHealthConstants {

    public static final int VITAL_TYPE_HEADER = 0;
    public static final int VITAL_TYPE_ITEM = 1;
    public static final String RESET_DEVICE = "RESET";
    public static final String CHANNEL_ID = "com.dhriti.wehealth.ANDROID";

    public static final String WE_HEALTH_DEV_URL = "http://localhost:8080/"; //"http://192.168.0.32:8080/";
    public static final String WE_HEALTH_GET_VITALS_LIST  = "getHealthVitals";
    public static final String WE_HEALTH_GET_VITALS_SOURCES_LIST = "getDevicesList";
    public static final String WE_HEALTH_REGISTER_FIREBASE_TOKEN = "registerToken";
    public static final String WE_HEALTH_RESET_SOURCES = "resetDevices";

    public static final String MQTT_SERVER_URI = "tcp://m16.cloudmqtt.com:16170";
    public static final String MQTT_CLIENT_ID = "com.dhriti.wehealth";
    public static final String MQTT_SUBSCRIPTION_TOPIC = "healthVitals/+";
    public static final String MQTT_USERNAME = "jggbujym";
    public static final String MQTT_PASSWORD = "mOCnFeDz4O3s";

    public static final int MQTT_BUFFER_SIZE = 100;
}
