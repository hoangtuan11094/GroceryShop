package com.example.groceryshop.activities.adapter;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.media.tv.TvContentRating;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.entity.VegetableEntity;

import java.util.ArrayList;

public class VegetableAdapter extends RecyclerView.Adapter<VegetableAdapter.HolderVegetableEtity> {
    private ArrayList<VegetableEntity> vegetableEntityArrayList;
    private Context context;

    public VegetableAdapter(ArrayList<VegetableEntity> vegetableEntityArrayList, Context context) {
        this.vegetableEntityArrayList = vegetableEntityArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public VegetableAdapter.HolderVegetableEtity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vegetable, parent, false);
        return new HolderVegetableEtity(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VegetableAdapter.HolderVegetableEtity holder, int position) {

        holder.imgProduct.setImageResource(vegetableEntityArrayList.get(position).getImgProduct());
        holder.tvNameProduct.setText(vegetableEntityArrayList.get(position).getProductName());
        holder.tvWeight.setText(String.valueOf(vegetableEntityArrayList.get(position).getProductWeight() + " gm"));
        holder.tvPrice.setText(String.valueOf( "$"+vegetableEntityArrayList.get(position).getProductPrice()));

        holder.clItemVegetable.getLayoutParams().width = getSizeScale(146);
        holder.clItemVegetable.getLayoutParams().height = getSizeScale(167);
        holder.imgProduct.getLayoutParams().width = getSizeScale(134);
        holder.imgProduct.getLayoutParams().height = getSizeScale(78);
        holder.imgBtnAdd.getLayoutParams().width = getSizeScale(89);
        holder.imgBtnAdd.getLayoutParams().height = getSizeScale(26);
    }

    @Override
    public int getItemCount() {
        return vegetableEntityArrayList.size();
    }

    public class HolderVegetableEtity extends RecyclerView.ViewHolder {
        private ImageView imgProduct;
        private TextView tvNameProduct;
        private TextView tvWeight;
        private TextView tvPrice;
        private ImageView imgBtnAdd;
        private View clItemVegetable;

        public HolderVegetableEtity(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgItem);
            tvNameProduct = itemView.findViewById(R.id.tvName);
            tvWeight = itemView.findViewById(R.id.tvWeight);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imgBtnAdd = itemView.findViewById(R.id.btnAdd);
            clItemVegetable = itemView.findViewById(R.id.clItemVegetable);
        }
    }

    //size
    private float scaleValue = 0;
    private DisplayMetrics displayMetrics;

    private DisplayMetrics getDisplayMetrics(){
        if (displayMetrics == null)
            displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics;
    }

    private int screenWidth = 0;

    protected int getScreenWidth(){
        if (screenWidth == 0)
            screenWidth = getDisplayMetrics().widthPixels;
        return screenWidth;
    }
    private float getScaleValue(){
        if (scaleValue == 0)
            scaleValue = getScreenWidth() * 1f / 375;
        return scaleValue;
    }
    protected  int getSizeScale(double sizeDesign){
        return (int) (sizeDesign * getScaleValue());
    }
}
