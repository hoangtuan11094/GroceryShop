package com.example.groceryshop.activities.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.adapter.VegetableAdapter;
import com.example.groceryshop.activities.conetant.PrefConstants;
import com.example.groceryshop.activities.data.DatabaseHelper;
import com.example.groceryshop.activities.entity.UserEntity;
import com.example.groceryshop.activities.entity.VegetableEntity;
import com.example.groceryshop.activities.listener.ListenerAPI;
import com.example.groceryshop.activities.network.DummyApi;
import com.example.groceryshop.activities.network.PrefNetword;

import java.util.ArrayList;
import java.util.List;

import static com.example.groceryshop.activities.network.DummyApi.getDummyApi;


public class FrmLogin extends BaseFragment implements View.OnClickListener {

    private EditText edtEmailLogin;
    private EditText edtPassLogin;
    private TextView tvForgotPass;
    private CheckBox ckbRemember;
    private String email;
    private String pass;
    private SharedPreferences sharedPreferences;

    public static FrmLogin getInstance() {
        return new FrmLogin();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frm_login;
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
        clLogin.getLayoutParams().height = activity.getSizeWithScale(209);

        View clEdtEmail = view.findViewById(R.id.clEdtEmail);
        clEdtEmail.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtEmail.getLayoutParams().height = activity.getSizeWithScale(37);

        View clEdtPass = view.findViewById(R.id.clEdtPass);
        clEdtPass.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtPass.getLayoutParams().height = activity.getSizeWithScale(37);

        View btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.getLayoutParams().width = activity.getSizeWithScale(284);
        btnLogin.getLayoutParams().height = activity.getSizeWithScale(37);

        View icKey = view.findViewById(R.id.icKey);
        icKey.getLayoutParams().height = activity.getSizeWithScale(11);
        icKey.getLayoutParams().width = activity.getSizeWithScale(11);

        edtEmailLogin = view.findViewById(R.id.edtEmailLogin);
        edtPassLogin = view.findViewById(R.id.edtPassLogin);
        ckbRemember = view.findViewById(R.id.ckbRemember);

        view.findViewById(R.id.tvSignUp).setOnClickListener(this);
        view.findViewById(R.id.tvForgotPass).setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = PrefNetword.getPrefNetword().newSharedPreferences(getContext());
        boolean check = sharedPreferences.getBoolean(PrefConstants.CHECK_SAVE_USER, false);
        if (check) {
            if (sharedPreferences != null) {
                edtEmailLogin.setText(sharedPreferences.getString(PrefConstants.EMAIL_SAVE_USER, ""));
                edtPassLogin.setText(sharedPreferences.getString(PrefConstants.PASSWORD_SAVE_USER, ""));
                ckbRemember.setChecked(check);
            }
        } else {
            edtEmailLogin.setText("");
            edtPassLogin.setText("");
            ckbRemember.setChecked(false);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSignUp:
                activity.showFrmSignUp();
                break;
            case R.id.btnLogin:
                loginUser();
                break;
            case R.id.tvForgotPass:
                activity.showFrmForgotPassword();
                break;
        }
    }

    //TODO Dummy API
    private ListenerAPI listenerAPI = new ListenerAPI() {
        @Override
        public void onStarts() {
            activity.showDialogLoading();
        }

        @Override
        public void onResult(boolean isSuccess) {
            activity.dismissDialog();

            if (isSuccess) {
                Log.e(TAG, "onResult: " + isSuccess );
                UserEntity userEntity = DatabaseHelper.getDatabaseHelper(getContext()).Login(new UserEntity(email, pass));
                if (userEntity != null) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(PrefConstants.EMAIL_SAVE_USER, email);
                    editor.putString(PrefConstants.PASSWORD_SAVE_USER, pass);
                    editor.putBoolean(PrefConstants.CHECK_SAVE_USER, ckbRemember.isChecked());
                    editor.putBoolean(PrefConstants.CHECK_LOGIN, true);
                    editor.commit();
                    showToast(R.string.lblLoggedInSuccessfully);
                    activity.showFrmHome();
//                    Log.e(TAG, "loginUser: " + userEntity.idUser + ", " + userEntity.passwordUser + ", " + userEntity.email + ", " + userEntity.fullName);
                } else {
                    showToast(R.string.lvlEmailOrPasswordIsIncorrect);
                }
            }else Log.e(TAG, "onResult: " + isSuccess );
        }
    };

    private void loginUser() {

        email = edtEmailLogin.getText().toString().trim();
        pass = edtPassLogin.getText().toString().trim();
        if (email.isEmpty() || pass.isEmpty()) {
            showToast(R.string.lblMustNotBeLeftBlank);
        } else {
            getDummyApi().start( listenerAPI);
        }


    }
}