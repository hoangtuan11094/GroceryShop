package com.example.groceryshop.activities.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.activities.ActMain;
import com.example.groceryshop.activities.data.DatabaseHelper;
import com.example.groceryshop.activities.entity.UserEntity;


public class FrmForgotPassword extends BaseFragment implements View.OnClickListener {
    private FragmentInterface listener;
    private EditText edtEmail;

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

        edtEmail = view.findViewById(R.id.edtEmail);
        btnSendLink.setOnClickListener(this);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendLink:
                sendLinkEmail();
                break;
        }
    }

    private void sendLinkEmail() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        databaseHelper.createDataBase();
        String email = edtEmail.getText().toString().trim();
        if (email.isEmpty()) {
            showToast(R.string.lblMustNotBeLeftBlank);
        } else {
            UserEntity userEntity = databaseHelper.sendLinkEmail(new UserEntity(email, null));
            if (userEntity != null) {
                Log.e(TAG, "loginUser: " + userEntity.idUser + ", " + userEntity.passwordUser + ", " + userEntity.email + ", " + userEntity.fullName);
                activity.showFrmResetPassword(userEntity.email);
            } else {
                showToast(R.string.lblEmailIsIncorrect);
            }
        }
    }
}