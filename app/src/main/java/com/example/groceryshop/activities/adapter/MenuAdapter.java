package com.example.groceryshop.activities.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.entity.MenuEntity;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MenuEntity> menuEntityList;
    private Context context;
    private int wclItemMenu;
    private int hclItemMenu;
    private int wIcon;
    private int hIcon;
    private int wArrow;
    private int hArrow;

    public interface OnClickItemListener {
        void onClicked(int position);

    }

    private OnClickItemListener onClickItemListener;

    public MenuAdapter(List<MenuEntity> menuEntityList, Context context, int wclItemMenu, int hclItemMenu, int wIcon, int hIcon, int wArrow, int hArrow, OnClickItemListener onClickItemListener) {
        this.menuEntityList = menuEntityList;
        this.context = context;
        this.wclItemMenu = wclItemMenu;
        this.hclItemMenu = hclItemMenu;
        this.wIcon = wIcon;
        this.hIcon = hIcon;
        this.wArrow = wArrow;
        this.hArrow = hArrow;
        this.onClickItemListener = onClickItemListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_no_select, parent, false);
            return new HolderItemNoSelect(view, wclItemMenu, hclItemMenu, wIcon, hIcon, wArrow, hArrow);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < menuEntityList.size(); i++) {
                    if (i == position) {
                        menuEntityList.get(position).setSelected(true);
                    } else {
                        menuEntityList.get(i).setSelected(false);
                    }
                }
                notifyDataSetChanged();
                if (onClickItemListener != null)
                    onClickItemListener.onClicked(position);
            }
        });
        if (menuEntityList   != null) {
            ((HolderItemNoSelect) holder).tvTitle.setText(menuEntityList.get(position).tvTitle);
            if (menuEntityList.get(position).isSelected) {
                ((HolderItemNoSelect) holder).imgIcon.setImageResource(menuEntityList.get(position).imgIconYellow);
                ((HolderItemNoSelect) holder).clItemMenu.setBackgroundResource(R.drawable.bg_menu_select);
                ((HolderItemNoSelect) holder).imgArrow.setImageResource(R.drawable.ic_arrow_yellow);
                ((HolderItemNoSelect) holder).tvTitle.setTextColor(context.getResources().getColor(R.color.colorYellow));
            } else {
                ((HolderItemNoSelect) holder).imgIcon.setImageResource(menuEntityList.get(position).imgIcon);
                ((HolderItemNoSelect) holder).clItemMenu.setBackgroundResource(R.drawable.bg_menu_no_select);
                ((HolderItemNoSelect) holder).imgArrow.setImageResource(R.drawable.ic_arrow_white);
                ((HolderItemNoSelect) holder).tvTitle.setTextColor(Color.WHITE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return menuEntityList == null ? 0 : menuEntityList.size();
    }


    public class HolderItemNoSelect extends RecyclerView.ViewHolder {
        private View clItemMenu;
        private ImageView imgIcon;
        private TextView tvTitle;
        private ImageView imgArrow;

        public HolderItemNoSelect(@NonNull View itemView, int wclItemMenu, int hclItemMenu, int wIcon, int hIcon, int wArrow, int hArrow) {
            super(itemView);
            clItemMenu = itemView.findViewById(R.id.clItemMenu);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            imgArrow = itemView.findViewById(R.id.imgArrow);

            clItemMenu.getLayoutParams().width = wclItemMenu;
            clItemMenu.getLayoutParams().height = hclItemMenu;

            imgIcon.getLayoutParams().width = wIcon;
            imgIcon.getLayoutParams().height = hIcon;

            imgArrow.getLayoutParams().width = wArrow;
            imgArrow.getLayoutParams().height = hArrow;
        }
    }
}
