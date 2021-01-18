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

public class VegetableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<VegetableEntity> vegetableEntityArrayList;
    private Context context;
    private int wclItem;
    private int hclItem;
    private int wImgProduct;
    private int hImgProduct;
    public interface OnClickItemListener {
        void onClicked(int position);
    }

    private OnClickItemListener onClickItemListener;


    public VegetableAdapter(ArrayList<VegetableEntity> vegetableEntityArrayList, Context context, int wclItem, int hclItem, int wImgProduct, int hImgProduct, OnClickItemListener onClickItemListener) {
        this.vegetableEntityArrayList = vegetableEntityArrayList;
        this.context = context;
        this.wclItem = wclItem;
        this.hclItem = hclItem;
        this.wImgProduct = wImgProduct;
        this.hImgProduct = hImgProduct;
        this.onClickItemListener = onClickItemListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
            return new HolderVegetableEtity1(view, wclItem, hclItem, wImgProduct, hImgProduct);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vegetable, parent, false);
            return new HolderVegetableEtity(view, wclItem, hclItem, wImgProduct, hImgProduct);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (vegetableEntityArrayList != null){
            switch (getItemViewType(position)){
                case 1:
                    ((HolderVegetableEtity1) holder).imgProduct.setText(vegetableEntityArrayList.get(position).getImgProduct());
                    ((HolderVegetableEtity1) holder).tvNameProduct.setText(vegetableEntityArrayList.get(position).getProductName());
                    ((HolderVegetableEtity1) holder).tvWeight.setText(String.valueOf(vegetableEntityArrayList.get(position).getProductWeight() + " gm"));
                    ((HolderVegetableEtity1) holder).tvPrice.setText(String.valueOf("$" + vegetableEntityArrayList.get(position).getProductPrice()));
                    break;
                case 2:
                    ((HolderVegetableEtity) holder).imgProduct.setText(vegetableEntityArrayList.get(position).getImgProduct());
                    ((HolderVegetableEtity) holder).tvNameProduct.setText(vegetableEntityArrayList.get(position).getProductName());
                    ((HolderVegetableEtity) holder).tvWeight.setText(String.valueOf(vegetableEntityArrayList.get(position).getProductWeight() + " gm"));
                    ((HolderVegetableEtity) holder).tvPrice.setText(String.valueOf("$" + vegetableEntityArrayList.get(position).getProductPrice()));
                    ((HolderVegetableEtity) holder).imgBtnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onClickItemListener != null){
                                onClickItemListener.onClicked(position);
                            }
                        }
                    });
                    break;
            }

//            ((HolderVegetableEtity1) holder).imgBtnAdd.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onClickItemListener != null){
//                        onClickItemListener.onClicked(position);
//                    }
//                }
//            });

        }

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

    @Override
    public int getItemViewType(int position) {
        if (position == 3){
            return 1;
        }else
            return 2;
    }

    public class HolderVegetableEtity1 extends RecyclerView.ViewHolder {
        private TextView imgProduct;
        private TextView tvNameProduct;
        private TextView tvWeight;
        private TextView tvPrice;
        private ImageView imgBtnAdd;
        private View clItemVegetable;

        public HolderVegetableEtity1(@NonNull View itemView, int wclItem, int hclItem, int wImgProduct, int hImgProduct) {
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
