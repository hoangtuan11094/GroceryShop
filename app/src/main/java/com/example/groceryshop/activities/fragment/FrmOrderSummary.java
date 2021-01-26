package com.example.groceryshop.activities.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.adapter.CartAdapter;
import com.example.groceryshop.activities.data.DatabaseHelper;
import com.example.groceryshop.activities.entity.CartEntity;

import java.util.ArrayList;
import java.util.List;


public class FrmOrderSummary extends BaseFragment implements View.OnClickListener {
    private RecyclerView rcOrderSummary;
    private CartAdapter cartAdapter;
    private ArrayList<CartEntity> cartEntityArrayList;

    @Override
    protected int getLayoutResId() {
        return R.layout.frm_order_summary;
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

        View clInformation = view.findViewById(R.id.clInformation);
        clInformation.getLayoutParams().height = activity.getSizeWithScale(92);

        View clTotal = view.findViewById(R.id.clTotal);
        clTotal.getLayoutParams().height = activity.getSizeWithScale(70);

        View clSubTotal = view.findViewById(R.id.clSubTotal);
        clSubTotal.getLayoutParams().width = activity.getSizeWithScale(138);
        clSubTotal.getLayoutParams().height = activity.getSizeWithScale(29);

        View btnConfirm = view.findViewById(R.id.btnConfirm);
        btnConfirm.getLayoutParams().width = activity.getSizeWithScale(137);
        btnConfirm.getLayoutParams().height = activity.getSizeWithScale(29);

        rcOrderSummary = view.findViewById(R.id.rcOrderSummary);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            if (cartEntityArrayList == null)cartEntityArrayList = new ArrayList<>();
            ArrayList<CartEntity> cartEntityArrayList1 = new ArrayList<>();
            cartEntityArrayList1.addAll(DatabaseHelper.getDatabaseHelper(getContext()).getAllCart());
            for (int i = 0; i < cartEntityArrayList1.size(); i++) {
                if (cartEntityArrayList1.get(i).quantity > 0){
                    cartEntityArrayList.add(cartEntityArrayList1.get(i));
                }
            }
            cartAdapter = new CartAdapter(getContext(), cartEntityArrayList, activity.getSizeWithScale(302), activity.getSizeWithScale(96), activity.getSizeWithScale(16),
                    activity.getSizeWithScale(16), activity.getSizeWithScale(13), activity.getSizeWithScale(19), new CartAdapter.OnClickItemListener() {
                @Override
                public void onClickDelete(int total) {

                }

                @Override
                public void onClickIncrease(int subtotal) {

                }

                @Override
                public void onClickReduction(int subtotal) {

                }
            });
            rcOrderSummary.setAdapter(cartAdapter);
            rcOrderSummary.setLayoutManager(new LinearLayoutManager(getContext()));
        }catch (Throwable e){
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {

    }

}