package com.example.groceryshop.activities.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.groceryshop.R;


public class FrmForgotPassword extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getLayoutResId() {
        return R.layout.frm_forgot_password;
    }

    @Override
    protected int getCurrentFragment() {
        return 0;
    }

    @Override
    protected void finish() {

    }

    @Override
    protected void loadControlsAndResize(View view) {
        View imgBg = view.findViewById(R.id.imgBg);
        imgBg.getLayoutParams().width = activity.getSizeWithScale(375);
        imgBg.getLayoutParams().height = activity.getSizeWithScale(667);

        View imgLogo = view.findViewById(R.id.imgLogo);
        imgLogo.getLayoutParams().height = activity.getSizeWithScale(164);
        imgLogo.getLayoutParams().width = activity.getSizeWithScale(211);

        View clLogin = view.findViewById(R.id.clLogin);
        clLogin.getLayoutParams().width = activity.getSizeWithScale(302);
        clLogin.getLayoutParams().height = activity.getSizeWithScale(143);

        View clEdtEmail = view.findViewById(R.id.clEdtEmail);
        clEdtEmail.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtEmail.getLayoutParams().height = activity.getSizeWithScale(37);

        View btnSendLink = view.findViewById(R.id.btnSendLink);
        btnSendLink.getLayoutParams().width = activity.getSizeWithScale(284);
        btnSendLink.getLayoutParams().height = activity.getSizeWithScale(37);
    }

    @Override
    public void onClick(View v) {

    }
}