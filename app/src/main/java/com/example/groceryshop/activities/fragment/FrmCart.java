package com.example.groceryshop.activities.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.groceryshop.R;


public class FrmCart extends BaseFragment implements View.OnClickListener {


    @Override
    protected int getLayoutResId() {
        return R.layout.frm_cart;
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

        View imgMenu = view.findViewById(R.id.imgMenu);
        imgMenu.getLayoutParams().width = activity.getSizeWithScale(20);
        imgMenu.getLayoutParams().height = activity.getSizeWithScale(18);

        View imgCart = view.findViewById(R.id.imgCart);
        imgCart.getLayoutParams().width = activity.getSizeWithScale(24);
        imgCart.getLayoutParams().height = activity.getSizeWithScale(24);

        View clFooter = view.findViewById(R.id.clFooter);
        clFooter.getLayoutParams().height = activity.getSizeWithScale(60);

        View btnCheckout = view.findViewById(R.id.btnCheckout);
        btnCheckout.getLayoutParams().width = activity.getSizeWithScale(284);
        btnCheckout.getLayoutParams().height = activity.getSizeWithScale(37);

        View clTotal = view.findViewById(R.id.clTotal);
        clTotal.getLayoutParams().width = activity.getSizeWithScale(302);
        clTotal.getLayoutParams().height = activity.getSizeWithScale(137);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }
}