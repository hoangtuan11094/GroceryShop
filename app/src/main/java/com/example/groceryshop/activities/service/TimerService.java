package com.example.groceryshop.activities.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.activities.ActMain;

public class TimerService extends IntentService {
    public static final String KEY_TYPE = "type";

    public TimerService() {
        super(TimerService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent notificationIntent = new Intent(this, ActMain.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        int requestID = (int) System.currentTimeMillis();
        PendingIntent contentIntent = PendingIntent.getActivity(this, requestID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_pause)
                .setContentTitle("Báo Thức")
                .setContentText("Báo Thức")
                .setPriority(6)
                .setVibrate(new long[]{0, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000})
                .setContentIntent(contentIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(10, builder.build());
    }
}
