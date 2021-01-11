package com.example.groceryshop.activities.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.activities.ActMain;


public class FrmWelcome extends BaseFragment implements View.OnClickListener {
    public static FrmWelcome getInstance() {
        return new FrmWelcome();
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.frm_welcome;
    }

    @Override
    protected int getCurrentFragment() {
        return 0;
    }

    @Override
    protected void finish() {

    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void loadControlsAndResize(View view) {
        View imgBg = view.findViewById(R.id.imgBg);
        imgBg.getLayoutParams().width = activity.getSizeWithScale(375);
        imgBg.getLayoutParams().height = activity.getSizeWithScale(667);

        View imgHeader = view.findViewById(R.id.imgHeader);
        imgHeader.getLayoutParams().width = activity.getSizeWithScale(375);
        imgHeader.getLayoutParams().height = activity.getSizeWithScale(240);

        View clFromLogin = view.findViewById(R.id.clFromLogin);
        clFromLogin.getLayoutParams().width = activity.getSizeWithScale(302);
        clFromLogin.getLayoutParams().height = activity.getSizeWithScale(410);

        View imgLogo = view.findViewById(R.id.imgLogo);
        imgLogo.getLayoutParams().height = activity.getSizeWithScale(182);
        imgLogo.getLayoutParams().width = activity.getSizeWithScale(219);

        View btnLoginWelCome = view.findViewById(R.id.btnLoginWelCome);
        btnLoginWelCome.getLayoutParams().height = activity.getSizeWithScale(37);
        btnLoginWelCome.getLayoutParams().width = activity.getSizeWithScale(284);

        View btnRegistrationWelCome = view.findViewById(R.id.btnRegistrationWelCome);
        btnRegistrationWelCome.getLayoutParams().height = activity.getSizeWithScale(37);
        btnRegistrationWelCome.getLayoutParams().width = activity.getSizeWithScale(284);

        View icKey = view.findViewById(R.id.icKey);
        icKey.getLayoutParams().height = activity.getSizeWithScale(11);
        icKey.getLayoutParams().width = activity.getSizeWithScale(11);

        View icUser = view.findViewById(R.id.icUser);
        icUser.getLayoutParams().height = activity.getSizeWithScale(11);
        icUser.getLayoutParams().width = activity.getSizeWithScale(14);

        btnLoginWelCome.setOnClickListener(this);
        btnRegistrationWelCome.setOnClickListener(this);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoginWelCome:
                activity.showFrmLogin();
                break;
            case R.id .btnRegistrationWelCome:
                activity.showFrmSignUp();
                break;
        }
    }
}