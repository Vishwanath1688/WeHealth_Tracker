package com.dhriti.wehealth.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.dhriti.wehealth.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class WeHealthUtils {

    public static boolean loadFragment(Fragment fragment, int containerId, Activity activity){
        if(fragment != null) {
            ((FragmentActivity)activity).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(containerId, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public static void sendNotification(String message, Context context) {
        Resources mResources = context.getResources();
        NotificationManager mNotificationManager = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mNotificationChannel = new NotificationChannel(WeHealthConstants.CHANNEL_ID,
                    mResources.getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationChannel.setDescription(message);
            mNotificationChannel.enableLights(true);
            mNotificationChannel.setLightColor(Color.RED);
            mNotificationChannel.enableVibration(false);
            mNotificationManager.createNotificationChannel(mNotificationChannel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, WeHealthConstants.CHANNEL_ID);
        mBuilder.setContentTitle(mResources.getString(R.string.app_name))
                .setContentText(message)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setChannelId(WeHealthConstants.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notifications_active_black_24dp));
        mNotificationManager.notify(0, mBuilder.build());
    }
}
