package com.example.groceryshop.activities.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.adapter.CartAdapter;
import com.example.groceryshop.activities.data.DatabaseHelper;
import com.example.groceryshop.activities.entity.CartEntity;

import java.util.ArrayList;


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
    private ViewGroup viewGroup;
    private TextView tvQuantityCart;


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

        viewGroup = view.findViewById(android.R.id.content);

        tvSubtotal = view.findViewById(R.id.tvSubtotal);
        tvDeliveryFee = view.findViewById(R.id.tvDeliveryFee);
        tvDiscount = view.findViewById(R.id.tvDiscount);
        tvTax = view.findViewById(R.id.tvTax);
        tvTotal = view.findViewById(R.id.tvTotal);

        tvQuantityCart = view.findViewById(R.id.tvQuantityCart);
        tvQuantityCart.setText(activity.getTvSizeCart());

        rcCart = view.findViewById(R.id.rcCart);
        imgMenu.setOnClickListener(this);

        btnCheckout.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDataRcCart();
        setSubtotal();
    }

    private void setDataRcCart() {
        if (cartEntityArrayList == null) cartEntityArrayList = new ArrayList<>();
        cartEntityArrayList = DatabaseHelper.getDatabaseHelper(getContext()).getAllCart();
        cartAdapter = new CartAdapter(getContext(), cartEntityArrayList, activity.getSizeWithScale(302), activity.getSizeWithScale(96), activity.getSizeWithScale(16),
                activity.getSizeWithScale(16), activity.getSizeWithScale(13), activity.getSizeWithScale(19), new CartAdapter.OnClickItemListener() {
            @Override
            public void onClickDelete(int position) {
                showDialogDelete(position);
            }

            @Override
            public void onClickIncrease(int subtotal) {
                subtotal1 = subtotal1 + subtotal;
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

    private void showDialogDelete(int position) {
        Dialog dialog = new Dialog(getContext(), R.style.dialogNotice);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_delete_row_cart, viewGroup, false);
        ImageView imgBtnDelete = view.findViewById(R.id.imgBtnDelete);
        ImageView imgBtnCancel = view.findViewById(R.id.imgBtnCancel);
        View clDialogDelete = view.findViewById(R.id.clDialogDelete);
        View clHeaderDialog = view.findViewById(R.id.clHeaderDialog);

        clHeaderDialog.getLayoutParams().height = activity.getSizeWithScale(48);
        imgBtnCancel.getLayoutParams().width = activity.getSizeWithScale(76);
        imgBtnCancel.getLayoutParams().height = activity.getSizeWithScale(27);
        imgBtnDelete.getLayoutParams().width = activity.getSizeWithScale(76);
        imgBtnDelete.getLayoutParams().height = activity.getSizeWithScale(27);
        clDialogDelete.getLayoutParams().width = activity.getSizeWithScale(302);
        clDialogDelete.getLayoutParams().height = activity.getSizeWithScale(170);

        dialog.setContentView(view);
        dialog.show();

        imgBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartEntityArrayList != null && cartEntityArrayList.size() > position) {
                    total = cartEntityArrayList.get(position).priceProduct * cartEntityArrayList.get(position).quantity;
                    DatabaseHelper.getDatabaseHelper(getContext()).deleteCart(cartEntityArrayList.get(position));
                    cartEntityArrayList.remove(position);
                    tvQuantityCart.setText(activity.getTvSizeCart());
                }
                subtotal1 = subtotal1 - total;
                cartAdapter.notifyDataSetChanged();
                setTotal();
                dialog.dismiss();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgMenu:
                activity.showMenu();
                break;
            case R.id.btnCheckout:
                activity.showFrmCheckoutAddress();
                break;
        }
    }
}