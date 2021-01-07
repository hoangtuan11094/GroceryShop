package com.example.groceryshop.activities.adapter;

import android.content.Context;
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
    private int wclItem;
    private int hclItem;
    private int wImgProduct;
    private int hImgProduct;

    public VegetableAdapter(ArrayList<VegetableEntity> vegetableEntityArrayList, Context context, int wclItem, int hclItem, int wImgProduct, int hImgProduct) {
        this.vegetableEntityArrayList = vegetableEntityArrayList;
        this.context = context;
        this.wclItem = wclItem;
        this.hclItem = hclItem;
        this.wImgProduct = wImgProduct;
        this.hImgProduct = hImgProduct;
    }

    @NonNull
    @Override
    public VegetableAdapter.HolderVegetableEtity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vegetable, parent, false);
        return new HolderVegetableEtity(view, wclItem, hclItem, wImgProduct, hImgProduct);
    }

    @Override
    public void onBindViewHolder(@NonNull VegetableAdapter.HolderVegetableEtity holder, int position) {

        holder.imgProduct.setText(vegetableEntityArrayList.get(position).getImgProduct());
        holder.tvNameProduct.setText(vegetableEntityArrayList.get(position).getProductName());
        holder.tvWeight.setText(String.valueOf(vegetableEntityArrayList.get(position).getProductWeight() + " gm"));
        holder.tvPrice.setText(String.valueOf("$" + vegetableEntityArrayList.get(position).getProductPrice()));
    }

    @Override
    public int getItemCount() {
        return vegetableEntityArrayList == null ? 0 :  vegetableEntityArrayList.size();
    }

    public class HolderVegetableEtity extends RecyclerView.ViewHolder {
        private TextView imgProduct;
        private TextView tvNameProduct;
        private TextView tvWeight;
        private TextView tvPrice;
        private ImageView imgBtnAdd;
        private View clItemVegetable;

        public HolderVegetableEtity(@NonNull View itemView, int wclItem, int hclItem, int wImgProduct, int hImgProduct) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgItem);
            tvNameProduct = itemView.findViewById(R.id.tvName);
            tvWeight = itemView.findViewById(R.id.tvWeight);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imgBtnAdd = itemView.findViewById(R.id.btnAdd);
            clItemVegetable = itemView.findViewById(R.id.clItemVegetable);

            clItemVegetable.getLayoutParams().width = wclItem;
            clItemVegetable.getLayoutParams().height = hclItem;
            imgProduct.getLayoutParams().width = wImgProduct;
            imgProduct.getLayoutParams().height = hImgProduct;
        }
    }

}
