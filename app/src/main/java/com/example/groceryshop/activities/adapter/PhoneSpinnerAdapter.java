package com.example.groceryshop.activities.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.media.tv.TvContentRating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.entity.PhoneCodeEntity;

import java.util.List;

public class PhoneSpinnerAdapter implements android.widget.SpinnerAdapter {
    private Context context;
    private List<PhoneCodeEntity> phoneCodeEntityList;

    public PhoneSpinnerAdapter(Context context, List<PhoneCodeEntity> phoneCodeEntityList) {
        this.context = context;
        this.phoneCodeEntityList = phoneCodeEntityList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.row_spinner_area_code_phone, parent, false);
        TextView tvAreaCode = convertView.findViewById(R.id.tvAreaCode);
        TextView imgFlag = convertView.findViewById(R.id.imgFlag);
        tvAreaCode.setText(phoneCodeEntityList.get(position).tvAreaCode);
        imgFlag.setText(phoneCodeEntityList.get(position).imgFlag);
        return convertView;
    }

    @Override
    public int getCount() {
        return phoneCodeEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return phoneCodeEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.row_spinner_area_code_phone, parent, false);

        TextView tvAreaCode = convertView.findViewById(R.id.tvAreaCode);
        TextView imgFlag = convertView.findViewById(R.id.imgFlag);
        tvAreaCode.setText(phoneCodeEntityList.get(position).tvAreaCode);
        imgFlag.setText(phoneCodeEntityList.get(position).imgFlag);
        return convertView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
