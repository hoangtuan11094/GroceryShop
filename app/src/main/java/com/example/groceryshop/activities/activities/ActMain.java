package com.example.groceryshop.activities.activities;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.fragment.FrmCategory;
import com.example.groceryshop.activities.fragment.FrmHome;
import com.example.groceryshop.activities.fragment.FrmLogin;
import com.example.groceryshop.activities.fragment.FrmResetPassword;
import com.example.groceryshop.activities.fragment.FrmSearchProduct;
import com.example.groceryshop.activities.fragment.FrmSignUp;
import com.example.groceryshop.activities.fragment.FrmWelcome;

public class ActMain extends BaseActivity {
    private final String TAG = "ActMain";

    public void addFragment(Fragment f) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameMenuContainer);
            if (currentFragment != null) {
                fragmentManager.beginTransaction()
                        .remove(currentFragment)
                        .commitAllowingStateLoss();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        addOrReplaceFragment(R.id.frameParent, f);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationApp();
    }

    private void navigationApp() {
        addFragment(new FrmWelcome());
    }


    //TODO show Fragment
    public void showFrmLogin() {
        addFragment(new FrmLogin());
    }

    public void showFrmSignUp() {
        addFragment(new FrmSignUp());
    }

    public void showFrmCategory(){
        addFragment(new FrmCategory());
    }

    public void showFrmHome(){
        addFragment(new FrmHome());
    }


    //TODO size manager
    private float scaleValue = 0;
    private DisplayMetrics displayMetrics;

    private DisplayMetrics getDisplayMetrics() {
        if (displayMetrics == null)
            displayMetrics = getResources().getDisplayMetrics();
        return displayMetrics;
    }


    private int screenWidth = 0;

    public int getScreenWidth() {
        if (screenWidth == 0)
            screenWidth = getDisplayMetrics().widthPixels;
        return screenWidth;
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