package com.example.groceryshop.activities.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.groceryshop.activities.data.PrefManager;

public class BaseActivity extends AppCompatActivity {

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
    private PrefManager prefManager;
    private PrefManager getPrefManager() {
        if(prefManager == null){
            prefManager = PrefManager.getInstance(this);
        }

        return prefManager;
    }
    public void prefWriteInt(String key, int value){
        try {
            getPrefManager().writeInt(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
