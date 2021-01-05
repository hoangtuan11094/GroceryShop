package com.example.groceryshop.activities.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.groceryshop.R;


public class FrmResetPassword extends BaseFragment implements View.OnClickListener {


    @Override
    protected int getLayoutResId() {
        return R.layout.frm_reset_password ;
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

        View clResetPass = view.findViewById(R.id.clResetPass);
        clResetPass.getLayoutParams().width = activity.getSizeWithScale(302);
        clResetPass.getLayoutParams().height = activity.getSizeWithScale(194);

        View clEdtNewPass = view.findViewById(R.id.clEdtNewPass);
        clEdtNewPass.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtNewPass.getLayoutParams().height = activity.getSizeWithScale(37);

        View clEdtReNewPass = view.findViewById(R.id.clEdtReNewPass);
        clEdtReNewPass.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtReNewPass.getLayoutParams().height = activity.getSizeWithScale(37);

        View btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.getLayoutParams().width = activity.getSizeWithScale(284);
        btnSubmit.getLayoutParams().height = activity.getSizeWithScale(37);
    }

    @Override
    public void onClick(View v) {

    }
}