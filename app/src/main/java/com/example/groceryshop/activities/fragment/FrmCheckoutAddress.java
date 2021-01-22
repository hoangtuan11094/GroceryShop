package com.example.groceryshop.activities.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.adapter.PhoneSpinnerAdapter;
import com.example.groceryshop.activities.entity.PhoneCodeEntity;

import java.util.ArrayList;
import java.util.List;


public class FrmCheckoutAddress extends BaseFragment implements View.OnClickListener {
    private Spinner spAreaCodePhone;
    private List<PhoneCodeEntity> phoneCodeEntityList;
    private EditText edtEmail;
    private  View clEdtEmail;
    @Override
    protected int getLayoutResId() {
        return R.layout.frm_checkout_address;
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
        View clHeader = view.findViewById(R.id.clHeader);
        clHeader.getLayoutParams().height = activity.getSizeWithScale(44);

        View imgBack = view.findViewById(R.id.imgBack);
        imgBack.getLayoutParams().width = activity.getSizeWithScale(20);
        imgBack.getLayoutParams().height = activity.getSizeWithScale(10);

        View clFooter = view.findViewById(R.id.clFooter);
        clFooter.getLayoutParams().height = activity.getSizeWithScale(60);

        View clAddress = view.findViewById(R.id.clAddress);
        clAddress.getLayoutParams().width = activity.getSizeWithScale(302);
        clAddress.getLayoutParams().height = activity.getSizeWithScale(490);

        View clEdtFirstName = view.findViewById(R.id.clEdtFirstName);
        clEdtFirstName.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtFirstName.getLayoutParams().height = activity.getSizeWithScale(36.68);

        View clEdtLastName = view.findViewById(R.id.clEdtLastName);
        clEdtLastName.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtLastName.getLayoutParams().height = activity.getSizeWithScale(36.68);

        View clEdtAddress = view.findViewById(R.id.clEdtAddress);
        clEdtAddress.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtAddress.getLayoutParams().height = activity.getSizeWithScale(36.68);

        View clEdtCity = view.findViewById(R.id.clEdtCity);
        clEdtCity.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtCity.getLayoutParams().height = activity.getSizeWithScale(36.68);

        View clEdtState = view.findViewById(R.id.clEdtState);
        clEdtState.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtState.getLayoutParams().height = activity.getSizeWithScale(36.68);

        View btnCheckout = view.findViewById(R.id.btnCheckout);
        btnCheckout.getLayoutParams().width = activity.getSizeWithScale(284);
        btnCheckout.getLayoutParams().height = activity.getSizeWithScale(37);

        clEdtEmail = view.findViewById(R.id.clEdtEmail);
        clEdtEmail.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtEmail.getLayoutParams().height = activity.getSizeWithScale(37);

        View clEdtPhone = view.findViewById(R.id.clEdtPhone);
        clEdtPhone.getLayoutParams().width = activity.getSizeWithScale(284);
        clEdtPhone.getLayoutParams().height = activity.getSizeWithScale(37);

        spAreaCodePhone = view.findViewById(R.id.spAreaCodePhone);
        edtEmail = view.findViewById(R.id.edtEmail);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showSpinner();

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isValidEmail(edtEmail.getText().toString())){
                    clEdtEmail.setBackgroundResource(R.drawable.bg_btn_white);
                }else  clEdtEmail.setBackgroundResource(R.drawable.bg_btn_error);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    private void showSpinner() {
        if (phoneCodeEntityList == null) phoneCodeEntityList = new ArrayList<>();
        phoneCodeEntityList.add(new PhoneCodeEntity("+1", "Img Flag"));
        phoneCodeEntityList.add(new PhoneCodeEntity("+2", "Img Flag"));
        phoneCodeEntityList.add(new PhoneCodeEntity("+3", "Img Flag"));
        phoneCodeEntityList.add(new PhoneCodeEntity("+4", "Img Flag"));
        phoneCodeEntityList.add(new PhoneCodeEntity("+5", "Img Flag"));
        phoneCodeEntityList.add(new PhoneCodeEntity("+6", "Img Flag"));
        phoneCodeEntityList.add(new PhoneCodeEntity("+7", "Img Flag"));
        phoneCodeEntityList.add(new PhoneCodeEntity("+8", "Img Flag"));
        phoneCodeEntityList.add(new PhoneCodeEntity("+9", "Img Flag"));
        phoneCodeEntityList.add(new PhoneCodeEntity("+10", "Img Flag"));
        phoneCodeEntityList.add(new PhoneCodeEntity("+11", "Img Flag"));
        phoneCodeEntityList.add(new PhoneCodeEntity("+12", "Img Flag"));

        PhoneSpinnerAdapter phoneSpinnerAdapter = new PhoneSpinnerAdapter(getContext(), phoneCodeEntityList);
        spAreaCodePhone.setAdapter(phoneSpinnerAdapter);

        spAreaCodePhone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}