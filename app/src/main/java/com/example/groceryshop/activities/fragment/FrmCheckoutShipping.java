package com.example.groceryshop.activities.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.groceryshop.R;


public class FrmCheckoutShipping extends BaseFragment implements View.OnClickListener {


    @Override
    protected int getLayoutResId() {
        return R.layout.frm_checkout_shipping;
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
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }
}