package com.example.groceryshop.activities.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.listener.ListenerAPI;
import com.example.groceryshop.activities.network.DummyApi;


public class FrmResetPassword extends BaseFragment implements View.OnClickListener {


    private String email;
    private EditText edtNewPass;
    private EditText edtReNewPass;
    private String newPass;
    private String reNewPass;

    @Override
    protected int getLayoutResId() {
        return R.layout.frm_reset_password;
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

        edtNewPass = view.findViewById(R.id.edtNewPass);
        edtReNewPass = view.findViewById(R.id.edtReNewPass);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            email = bundle.getString("email");
            Log.e(TAG, "onViewCreated: " + email);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                resetPass();
                break;
        }
    }

    //TODO dummy API
    private ListenerAPI listenerAPI = new ListenerAPI() {
        @Override
        public void onStarts() {
            activity.showDialogLoading();
        }

        @Override
        public void onResult(boolean isSuccess) {
            int count = activity.databaseHelper.resetPassword(email, newPass);
            if (count > 0) {
                showToast(R.string.lblPasswordWasSuccessfullyChanged);
                edtReNewPass.setText("");
                edtNewPass.setText("");
                Log.e(TAG, "resetPass: " + count);
                activity.showFrmLogin();
            }

            activity.dismissDialog();
        }
    };

    private void resetPass() {
        newPass = edtNewPass.getText().toString().trim();
        reNewPass = edtReNewPass.getText().toString().trim();

        if (newPass.isEmpty() || newPass.isEmpty()) {
            showToast(R.string.lblMustNotBeLeftBlank);
        } else if (!newPass.equals(reNewPass)) {
            showToast(R.string.lblPasswordsAreNotTheSame);
        } else {
            DummyApi.getDummyApi().onStart(listenerAPI);
        }
    }
}