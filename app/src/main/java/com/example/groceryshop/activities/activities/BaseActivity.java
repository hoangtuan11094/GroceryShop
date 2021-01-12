package com.example.groceryshop.activities.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;
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
                        .commitAllowingStateLoss();
            }else {
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
}
