package com.example.groceryshop.activities.fragment;

import android.media.MediaDrm;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.adapter.CategoryAdapter;
import com.example.groceryshop.activities.data.DatabaseHelper;
import com.example.groceryshop.activities.entity.CategoryEntity;
import com.example.groceryshop.activities.listener.ListenerAPI;
import com.example.groceryshop.activities.network.DummyApi;

import java.util.ArrayList;
import java.util.List;

import static com.example.groceryshop.activities.network.DummyApi.getDummyApi;


public class FrmCategory extends BaseFragment implements View.OnClickListener {

    //    private int[] intImg = {R.drawable.item_category1, R.drawable.item_category2, R.drawable.item_category3, R.drawable.item_category4, R.drawable.item_category5, R.drawable.item_category6,
//            R.drawable.item_category7, R.drawable.item_category8, R.drawable.item_category9,R.drawable.item_category10 };
    private List<CategoryEntity> categoryEntityList;
    private RecyclerView rcCategory;
    private CategoryAdapter categoryAdapter;


    @Override
    protected int getLayoutResId() {
        return R.layout.frm_category;
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

        rcCategory = view.findViewById(R.id.rcCategory);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setImgItemCategory();
    }

    //TODO dummy API
    private ListenerAPI listenerAPI = new ListenerAPI() {
        @Override
        public void onStarts() {
            activity.showDialogLoading();
        }

        @Override
        public void onResult(boolean isSuccess) {
            if (categoryEntityList == null){
                categoryEntityList = new ArrayList<>();
                categoryEntityList.addAll(DatabaseHelper.getDatabaseHelper(getContext()).getAllCategory());
            }
            if (categoryAdapter == null){
                categoryAdapter = new CategoryAdapter(categoryEntityList, getContext(), activity.getSizeWithScale(269), activity.getSizeWithScale(43));
            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            rcCategory.setLayoutManager(linearLayoutManager);
            rcCategory.setAdapter(categoryAdapter);
            activity.dismissDialog();
        }
    };

    private void setImgItemCategory() {
        getDummyApi().start(listenerAPI);
    }

    @Override
    public void onClick(View v) {

    }
}