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
import com.example.groceryshop.activities.entity.CartEntity;

import java.util.ArrayList;

public class OrderSummaryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<CartEntity> entityArrayList;
    private int wclItemCart;
    private int hclItemCart;
    private int wImgMinus;
    private int hImgMinus;
    private int wImgDelete;
    private int hImgDelete;

    public interface OnClickItemListener {
        void onClickDelete(int total);

        void onClickIncrease(int subtotal);

        void onClickReduction(int subtotal);
    }

    private OnClickItemListener onClickItemListener;


    public OrderSummaryAdapter(Context context, ArrayList<CartEntity> entityArrayList, int wclItemCart, int hclItemCart, int wImgMinus, int hImgMinus, int wImgDelete, int hImgDelete, OnClickItemListener onClickItemListener) {
        this.context = context;
        this.entityArrayList = entityArrayList;
        this.wclItemCart = wclItemCart;
        this.hclItemCart = hclItemCart;
        this.wImgMinus = wImgMinus;
        this.hImgMinus = hImgMinus;
        this.wImgDelete = wImgDelete;
        this.hImgDelete = hImgDelete;
        this.onClickItemListener = onClickItemListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new HolderOrderSummary(view, wclItemCart, hclItemCart, wImgMinus, hImgMinus, wImgDelete, hImgDelete);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HolderOrderSummary extends RecyclerView.ViewHolder{
        private View clItemCart;
        private TextView imgProduct;
        private TextView tvNameProduct;
        private TextView tvPrice;
        private TextView tvQuantity;
        private ImageView imgMinus;
        private ImageView imgPlus;
        private ImageView imgDelete;

        public HolderOrderSummary(@NonNull View itemView, int wclItemCart, int hclItemCart, int wImgMinus, int hImgMinus, int wImgDelete, int hImgDelete) {
            super(itemView);
            clItemCart = itemView.findViewById(R.id.clItemCart);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            imgMinus = itemView.findViewById(R.id.imgMinus);
            imgPlus = itemView.findViewById(R.id.imgPlus);
            imgDelete = itemView.findViewById(R.id.imgDelete);

            clItemCart.getLayoutParams().width = wclItemCart;
            clItemCart.getLayoutParams().height = hclItemCart;
            imgMinus.getLayoutParams().width = wImgMinus;
            imgMinus.getLayoutParams().height = hImgMinus;
            imgPlus.getLayoutParams().width = wImgMinus;
            imgPlus.getLayoutParams().height = hImgMinus;
            imgDelete.getLayoutParams().width = wImgDelete;
            imgDelete.getLayoutParams().height = hImgDelete;
        }
    }
}
