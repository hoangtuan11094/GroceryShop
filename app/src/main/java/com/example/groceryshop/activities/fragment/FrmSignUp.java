package com.example.groceryshop.activities.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.fragment.BaseFragment;


public class FrmSignUp extends BaseFragment implements View.OnClickListener {

    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPass;


    @Override
    protected int getLayoutResId() {
        return R.layout.frm_signup;
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
        clLogin.getLayoutParams().height = activity.getSizeWithScale(248);

        View btnSignUp = view.findViewById(R.id.btnSignUp);
        btnSignUp.getLayoutParams().width = activity.getSizeWithScale(284);
        btnSignUp.getLayoutParams().height = activity.getSizeWithScale(37);

        View clEdtName = view.findViewById(R.id.clEdtName);
        clEdtName.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtName.getLayoutParams().height = activity.getSizeWithScale(37);

        View clEdtEmail = view.findViewById(R.id.clEdtEmail);
        clEdtEmail.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtEmail.getLayoutParams().height = activity.getSizeWithScale(37);

        View clEdtPass = view.findViewById(R.id.clEdtPass);
        clEdtPass.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtPass.getLayoutParams().height = activity.getSizeWithScale(37);

    }

    @Override
    public void onClick(View v) {

    }
}