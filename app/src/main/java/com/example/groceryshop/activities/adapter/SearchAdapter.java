package com.example.groceryshop.activities.adapter;

import android.content.Context;
import android.media.tv.TvContentRating;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.entity.VegetableEntity;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable{
    private ArrayList<VegetableEntity> vegetableEntityList;
    private ArrayList<VegetableEntity> vegetableEntityListFilter;
    private Context context;
    private int wClItemSearch;
    private int hClItemSearch;
    private int wImgBtnAdd;
    private int hImgBtnAdd;

    public SearchAdapter(ArrayList<VegetableEntity> vegetableEntityList, Context context, int wClItemSearch, int hClItemSearch, int wImgBtnAdd, int hImgBtnAdd) {
        this.vegetableEntityList = vegetableEntityList;
        this.vegetableEntityListFilter = vegetableEntityList;
        this.context = context;
        this.wClItemSearch = wClItemSearch;
        this.hClItemSearch = hClItemSearch;
        this.wImgBtnAdd = wImgBtnAdd;
        this.hImgBtnAdd = hImgBtnAdd;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new HolderSearchItem(view, wClItemSearch, hClItemSearch, wImgBtnAdd, hImgBtnAdd);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final VegetableEntity vegetableEntity = vegetableEntityListFilter.get(position);
        if (vegetableEntityListFilter != null) {
            ((HolderSearchItem) holder).imgProduct.setText(vegetableEntity.getImgProduct());
            ((HolderSearchItem) holder).tvNameProduct.setText(vegetableEntity.getProductName());
            ((HolderSearchItem) holder).tvWeight.setText(String.valueOf(vegetableEntity.getProductWeight() + " gm"));
            ((HolderSearchItem) holder).tvPrice.setText(String.valueOf("$" +vegetableEntity.getProductPrice()));
        }
    }

    @Override
    public int getItemCount() {
        return vegetableEntityListFilter == null ? 0 : vegetableEntityListFilter.size();
    }



    public class HolderSearchItem extends RecyclerView.ViewHolder {
        private TextView imgProduct;
        private TextView tvNameProduct;
        private TextView tvWeight;
        private TextView tvPrice;
        private ImageView imgBtnAdd;
        private View clItemSearch;

        public HolderSearchItem(@NonNull View itemView, int wClItemSearch, int hClItemSearch, int wImgBtnAdd, int hImgBtnAdd) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            imgBtnAdd = itemView.findViewById(R.id.imgBtnAdd);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvWeight = itemView.findViewById(R.id.tvWeight);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            clItemSearch = itemView.findViewById(R.id.clItemSearch);

            clItemSearch.getLayoutParams().width = wClItemSearch;
            clItemSearch.getLayoutParams().height = hClItemSearch;
            imgBtnAdd.getLayoutParams().width = wImgBtnAdd;
            imgBtnAdd.getLayoutParams().height = hImgBtnAdd;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()){
                    vegetableEntityListFilter = vegetableEntityList;
                }else {
                    ArrayList<VegetableEntity> vegetableEntities = new ArrayList<>();
                    for (VegetableEntity row : vegetableEntityList){
                        if (row.getProductName().toLowerCase().contains(charString.toLowerCase())){
                            vegetableEntities.add(row);
                        }
                    }
                    vegetableEntityListFilter = vegetableEntities;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = vegetableEntityListFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                vegetableEntityListFilter = (ArrayList<VegetableEntity>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    public interface VegetableAdapterListener {
        void onVegetableSelected(VegetableEntity vegetableEntity);
    }
}
