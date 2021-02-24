package com.example.groceryshop.activities.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.groceryshop.activities.activities.ActMain;
import com.example.groceryshop.activities.data.DatabaseHelper;

public abstract class BaseFragment extends Fragment {

    protected String TAG = this.getClass().getSimpleName();

    protected abstract int getLayoutResId();
    protected abstract int getCurrentFragment();

    protected abstract void finish();
    public boolean isBackPreviousEnable(){
        return false;
    }
    public void backToPrevious(){}

    protected ActMain activity;
    protected void showToast(String msg){
        try {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    protected void showToast(int msg){
        try {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean mIsClickAble = true;
    private Handler mHandlerClick = new Handler();
    private Runnable changeStateClickAble = new Runnable() {
        @Override
        public void run() {
            mIsClickAble = true;
        }
    };
    protected abstract void loadControlsAndResize(View view);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        if(activity == null)
            activity = (ActMain) getActivity();
        loadControlsAndResize(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(activity == null)
            activity = (ActMain) getActivity();
    }

    @Override
    public void onDestroy() {
        try {
            mHandlerClick.removeCallbacks(changeStateClickAble);
            System.gc();
        }catch (Throwable e){
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
