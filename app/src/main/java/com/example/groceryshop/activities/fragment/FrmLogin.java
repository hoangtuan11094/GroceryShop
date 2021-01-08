package com.example.groceryshop.activities.fragment;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.adapter.VegetableAdapter;
import com.example.groceryshop.activities.data.DatabaseHelper;
import com.example.groceryshop.activities.entity.UserEntity;
import com.example.groceryshop.activities.entity.VegetableEntity;

import java.util.ArrayList;
import java.util.List;


public class FrmLogin extends BaseFragment implements View.OnClickListener {

    private EditText edtEmailLogin;
    private EditText edtPassLogin;
    private TextView tvForgotPass;

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

        view.findViewById(R.id.tvSignUp).setOnClickListener(this);
        view.findViewById(R.id.tvForgotPass).setOnClickListener(this);
        btnLogin.setOnClickListener(this);
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

    private void loginUser() {
        String email = edtEmailLogin.getText().toString().trim();
        String pass = edtPassLogin.getText().toString().trim();
        if (email.isEmpty() || pass.isEmpty()) {
            showToast(R.string.lblMustNotBeLeftBlank);
        } else {
            UserEntity userEntity = activity.databaseHelper.Login(new UserEntity( email, pass));
            if (userEntity != null) {
                showToast(R.string.lblLoggedInSuccessfully);
                activity.showFrmHome();
                Log.e(TAG, "loginUser: "+ userEntity.idUser + ", " + userEntity.passwordUser +", "+ userEntity.email + ", " + userEntity.fullName  );
            } else {
                showToast(R.string.lvlEmailOrPasswordIsIncorrect);
            }

        }
    }
}