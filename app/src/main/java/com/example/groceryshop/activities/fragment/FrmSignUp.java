package com.example.groceryshop.activities.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.data.DatabaseHelper;
import com.example.groceryshop.activities.entity.UserEntity;
import com.example.groceryshop.activities.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;


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

        edtName = view.findViewById(R.id.edtName);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPass = view.findViewById(R.id.edtPass);

        view.findViewById(R.id.btnSignUp).setOnClickListener(this);
        view.findViewById(R.id.tvSignIn).setOnClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignUp:
                insertUser();
                break;
            case R.id.tvSignIn:
                activity.showFrmLogin();
                break;
        }
    }

    private void insertUser(){
        activity.databaseHelper.getAllUser();
        UserEntity userEntity = new UserEntity();
        userEntity.email = edtEmail.getText().toString().trim();
        userEntity.passwordUser = edtPass.getText().toString().trim();
        userEntity.fullName = edtName.getText().toString().trim();
        boolean checkEmail = activity.databaseHelper.checkEmail(userEntity.email);
        if (userEntity.email.isEmpty() || userEntity.passwordUser.isEmpty() || userEntity.fullName.isEmpty()){
            showToast(R.string.lblMustNotBeLeftBlank);
        }else if (!isValidEmail(userEntity.email)){
            showToast(R.string.lblEmailFormatIsIncorrect);
        }
        else if (checkEmail){
            showToast(R.string.lbl_EmailAlreadyExists);
        }
        else {
        activity.databaseHelper.insertUser(userEntity);
        showToast(R.string.lbl_SignUpSuccess);
        activity.showFrmLogin();
        edtEmail.setText("");
        edtName.setText("");
        edtPass.setText("");
        }
    }

    static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}