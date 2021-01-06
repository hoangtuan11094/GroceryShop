package com.example.groceryshop.activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryshop.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.HolderCategory> {
    private int[] intImg;
    private Context context;
    private int wImgItem;
    private int hImgItem;

    public CategoryAdapter(int[] intImg, Context context, int wImgItem, int hImgItem) {
        this.intImg = intImg;
        this.context = context;
        this.wImgItem = wImgItem;
        this.hImgItem = hImgItem;
    }

    @NonNull
    @Override
    public HolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);

        return new HolderCategory(view, wImgItem,hImgItem);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCategory holder, int position) {
        holder.imgItemCategory.setImageResource(intImg[position]);
    }

    @Override
    public int getItemCount() {
        return intImg.length;
    }

    public class HolderCategory extends RecyclerView.ViewHolder {
        private ImageView imgItemCategory;

        public HolderCategory(@NonNull View itemView , int wItemImg, int hItemImg) {
            super(itemView);
            imgItemCategory = itemView.findViewById(R.id.itemCategory);
            imgItemCategory.getLayoutParams().width = wItemImg;
            imgItemCategory.getLayoutParams().height = hItemImg;
        }
    }
}
