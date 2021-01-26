package com.example.groceryshop.activities.fragment;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.data.DatabaseHelper;
import com.example.groceryshop.activities.entity.VegetableEntity;

public class FrmProductDetails extends BaseFragment implements View.OnClickListener {

    private TextView tvQuantityCart;
    private int idProduct;
    private VegetableEntity product;
    private TextView tvNameProduct;
    private TextView tvPriceSale;
    private TextView tvPrice;
    private TextView tvProductDescription;

    @Override
    protected int getLayoutResId() {
        return R.layout.frm_product_details;
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

        View clImgProductDetails = view.findViewById(R.id.clImgProductDetails);
        clImgProductDetails.getLayoutParams().height = activity.getSizeWithScale(185);

        View clProductDescription = view.findViewById(R.id.clProductDescription);
        clProductDescription.getLayoutParams().height = activity.getSizeWithScale(298);

        tvNameProduct = view.findViewById(R.id.tvNameProduct);
        tvPriceSale = view.findViewById(R.id.tvPriceSale);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvProductDescription = view.findViewById(R.id.tvProductDescription);
        tvProductDescription.setMovementMethod(new ScrollingMovementMethod());
        tvQuantityCart = view.findViewById(R.id.tvQuantityCart);
        tvQuantityCart.setText(activity.getTvSizeCart());

        imgMenu.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            idProduct = bundle.getInt("id");
        }
        showProductDetails();

    }

    @Override
    public void onClick(View v) {

    }

    private void showProductDetails() {
        if (product == null)
            product = new VegetableEntity();
        product = DatabaseHelper.getDatabaseHelper(getContext()).getInformationProduct(idProduct);
        tvNameProduct.setText(product.getProductName());
        tvPrice.setText(String.valueOf(product.getProductPrice()));
        tvProductDescription.setText(product.getProductDescription());
    }
}