package com.example.groceryshop.activities.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.adapter.CartAdapter;
import com.example.groceryshop.activities.data.DatabaseHelper;
import com.example.groceryshop.activities.entity.CartEntity;

import java.util.ArrayList;
import java.util.List;


public class FrmCart extends BaseFragment implements View.OnClickListener {

    private RecyclerView rcCart;
    private CartAdapter cartAdapter;
    private int subtotal1 = 0;
    private int total = 0;
    private TextView tvSubtotal;
    private TextView tvDeliveryFee;
    private TextView tvDiscount;
    private TextView tvTax;
    private TextView tvTotal;
    private ArrayList<CartEntity> cartEntityArrayList;

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

        tvSubtotal = view.findViewById(R.id.tvSubtotal);
        tvDeliveryFee = view.findViewById(R.id.tvDeliveryFee);
        tvDiscount = view.findViewById(R.id.tvDiscount);
        tvTax = view.findViewById(R.id.tvTax);
        tvTotal = view.findViewById(R.id.tvTotal);

        rcCart = view.findViewById(R.id.rcCart);
        imgMenu.setOnClickListener(this);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDataRcCart();
        setSubtotal();
    }

    private void setDataRcCart() {
        if (cartEntityArrayList == null)cartEntityArrayList = new ArrayList<>();
        cartEntityArrayList = DatabaseHelper.getDatabaseHelper(getContext()).getAllCart();
        cartAdapter = new CartAdapter(getContext(), cartEntityArrayList, activity.getSizeWithScale(302), activity.getSizeWithScale(96), activity.getSizeWithScale(16),
                activity.getSizeWithScale(16), activity.getSizeWithScale(13), activity.getSizeWithScale(19), new CartAdapter.OnClickItemListener() {
            @Override
            public void onClickDelete(int total) {
                subtotal1 = subtotal1 - total;
                setTotal();
            }

            @Override
            public void onClickIncrease(int subtotal) {
                subtotal1  = subtotal1 + subtotal;
                setTotal();
            }

            @Override
            public void onClickReduction(int subtotal) {
                    subtotal1 = subtotal1 - subtotal;
                    setTotal();
            }
        });
        rcCart.setLayoutManager(new LinearLayoutManager(getContext()));
        rcCart.setAdapter(cartAdapter);
    }

    private void setSubtotal() {
        for (int i = 0; i < cartEntityArrayList.size(); i++) {
            subtotal1 = subtotal1 + cartEntityArrayList.get(i).quantity * cartEntityArrayList.get(i).priceProduct;
        }
        setTotal();
    }

    private void setTotal() {
        tvSubtotal.setText("$" + String.valueOf(subtotal1));
        total = subtotal1 + subtotal1 * 10 / 100;
        tvTotal.setText("$" + String.valueOf(total));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgMenu:
                activity.showMenu();
                break;
        }
    }
}