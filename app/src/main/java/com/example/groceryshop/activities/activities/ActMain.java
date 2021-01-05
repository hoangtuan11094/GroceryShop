package com.example.groceryshop.activities.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.data.PrefManager;
import com.example.groceryshop.activities.fragment.FrmForgotPassword;
import com.example.groceryshop.activities.fragment.FrmHome;
import com.example.groceryshop.activities.fragment.FrmLogin;
import com.example.groceryshop.activities.fragment.FrmResetPassword;
import com.example.groceryshop.activities.fragment.FrmSignUp;
import com.example.groceryshop.activities.fragment.FrmWelcome;

public class ActMain extends BaseActivity {
    private final String TAG = "ActMain";
    private ActMain _activity;
    private int currentFragment;

    public void setCurrentScreen(int currentFragment) {
        this.currentFragment = currentFragment;
    }

    public void addFragment(Fragment f){
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameMenuContainer);
            if(currentFragment != null){
                fragmentManager.beginTransaction()
                        .remove(currentFragment)
                        .commitAllowingStateLoss();
            }
        }catch (Throwable e){e.printStackTrace();}
        addOrReplaceFragment(R.id.frameParent, f);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        clearBadgeNotification("onCreate");
        navigationApp();
    }

    private void navigationApp() {
        addFragment(new FrmHome());
    }

//    private void clearBadgeNotification(String from){
//        try {
//            prefWriteInt(PrefManager.NOTIFICATION_COUNT, 0);
//            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
//        }catch (Throwable e){e.printStackTrace();}
//    }

    //TODO show Fragment
    public void showFrmLogin(){
        addFragment(new FrmLogin());
    }
    public void showFrmSignUp(){
        addFragment(new FrmSignUp());
    }





    //TODO size manager
    private float scaleValue = 0;
    private DisplayMetrics displayMetrics;

    private DisplayMetrics getDisplayMetrics() {
        if (displayMetrics == null)
            displayMetrics = getResources().getDisplayMetrics();
        return displayMetrics;
    }

    private float screenDensity = 0;

    public float getScreenDensity() {
        if (screenDensity == 0)
            screenDensity = getDisplayMetrics().density;
        return screenDensity;
    }

    private int screenWidth = 0;

    public int getScreenWidth() {
        if (screenWidth == 0)
            screenWidth = getDisplayMetrics().widthPixels;
        return screenWidth;
    }

    private int screenHeight = 0;

    public int getScreenHeight() {
        if (screenHeight == 0) {
            int statusBarHeight = 0;
            try {
                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    statusBarHeight = getResources().getDimensionPixelSize(resourceId);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
            screenHeight = getDisplayMetrics().heightPixels - statusBarHeight;
        }
        return screenHeight;
    }

    private float getScaleValue() {
        if (scaleValue == 0)
            scaleValue = getScreenWidth() * 1f / 375;
        return scaleValue;
    }

    public int getSizeWithScale(double sizeDesign) {
        return (int) (sizeDesign * getScaleValue());
    }
}