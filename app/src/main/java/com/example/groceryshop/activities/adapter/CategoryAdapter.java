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
import com.example.groceryshop.activities.entity.CategoryEntity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.HolderCategory> {
    private List<CategoryEntity> categoryEntities;
    private Context context;
    private int wImgItem;
    private int hImgItem;

    public CategoryAdapter(List<CategoryEntity> categoryEntities, Context context, int wImgItem, int hImgItem) {
        this.categoryEntities = categoryEntities;
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
        holder.imgItemCategory.setText(categoryEntities.get(position).getImgCategory());
        holder.tvNameCategory.setText(categoryEntities.get(position).getNameCategory());
    }

    @Override
    public int getItemCount() {

        return categoryEntities == null ? 0 : categoryEntities.size();
    }

    public class HolderCategory extends RecyclerView.ViewHolder {
        private TextView imgItemCategory;
        private TextView tvNameCategory;

        public HolderCategory(@NonNull View itemView , int wItemImg, int hItemImg) {
            super(itemView);
            imgItemCategory = itemView.findViewById(R.id.itemCategory);
            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
            imgItemCategory.getLayoutParams().width = wItemImg;
            imgItemCategory.getLayoutParams().height = hItemImg;
        }
    }
}
