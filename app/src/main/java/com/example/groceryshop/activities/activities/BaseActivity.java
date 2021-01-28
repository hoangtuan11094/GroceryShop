package com.example.groceryshop.activities.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.app.Notification.Action;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.groceryshop.R;

public class BaseActivity extends AppCompatActivity {
    private Dialog mProgressDialog;
    public void addOrReplaceFragment(int idContent, Fragment f){
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment currentFragment = fragmentManager.findFragmentById(idContent);
            if(currentFragment!=null){
                fragmentManager.beginTransaction()
                        .replace(idContent, f)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
            else {
                fragmentManager.beginTransaction()
                        .add(idContent, f)
                        .commitAllowingStateLoss();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //TODO == pref manager ============================

    public void initDialogLoading() {
        mProgressDialog = new Dialog(this, R.style.dialogNotice);
        mProgressDialog.setContentView(R.layout.dialog_progress);
    }

    public void showDialogLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
            mProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    return keyCode == KeyEvent.KEYCODE_BACK;
                }
            });
        }
    }

    public void dismissDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    //TODO check Internet
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            if (!connected){
                showDialogNoInternet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connected;
    }

    public void showDialogNoInternet() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        View alertLayout = LayoutInflater.from(this).inflate(R.layout.dialog_no_internet, (LinearLayout) findViewById(R.id.layout_content));
        Button btn_close;
        btn_close = alertLayout.findViewById(R.id.btn_close);
        alert.setView(alertLayout);
        alert.setCancelable(true);
        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //TODO Notification
    public static final String KEY_TEXT_REPLY = "key_text_reply";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public void showNotification(String title, String message, int icon, int icClose){

        Intent intent = new Intent(this, ActMain.class);
//        PendingIntent pIntent = PendingIntent
//                .getActivity(this, (int) System.currentTimeMillis(), intent, 0);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
//                .setSmallIcon(icon)
//                .setContentTitle(title)
//                .setContentText(message)
//                .setFullScreenIntent(pIntent, false)
//                .setPriority(NotificationCompat.PRIORITY_LOW);
//               NotificationManager notificationManager = (NotificationManager) getSystemService(
//               Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, builder.build());

        String replyLabel = "Enter your reply here";
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent dismissIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                icon, "REPLY", resultPendingIntent)
                .addRemoteInput(remoteInput)
                .setAllowGeneratedReplies(true)
                .build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_envelope)
                .setContentTitle(title)
                .setContentText(message)
                .addAction(replyAction)
                .addAction(icClose, "DISMISS", dismissIntent);

        intent.putExtra("notificationId", 1);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    public String dataNotification(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        String reply = null;
        if (remoteInput != null) {
            reply = remoteInput.getCharSequence(KEY_TEXT_REPLY).toString();
            NotificationCompat.Builder repliedNotification = new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_envelope)
                            .setContentText("OK");

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(2, repliedNotification.build());

        }
        return reply;
    }

}
