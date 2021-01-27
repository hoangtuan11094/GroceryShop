package com.example.groceryshop.activities.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.fragment.FrmHome;
import com.google.android.material.circularreveal.CircularRevealHelper;

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
    public void showNotification(String title, String message){
    }
}
