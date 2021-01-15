package com.example.groceryshop.activities.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.adapter.SearchAdapter;
import com.example.groceryshop.activities.adapter.VegetableAdapter;
import com.example.groceryshop.activities.data.DatabaseHelper;
import com.example.groceryshop.activities.entity.VegetableEntity;
import com.example.groceryshop.activities.listener.ListenerAPI;
import com.example.groceryshop.activities.network.DummyApi;

import java.util.ArrayList;
import java.util.List;

public class FrmSearchProduct extends BaseFragment implements View.OnClickListener {
    private RecyclerView rcSearch;
    private ArrayList<VegetableEntity> vegetableEntityArrayList;
    private SearchAdapter searchAdapter;
    private EditText edtSearch;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frm_search_product;
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

        View clSearch = view.findViewById(R.id.clSearch);
        clSearch.getLayoutParams().width = activity.getSizeWithScale(209);
        clSearch.getLayoutParams().height = activity.getSizeWithScale(29);

        rcSearch = view.findViewById(R.id.rcSearch);
        edtSearch = view.findViewById(R.id.edtSearch);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showDataProduct();
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String tvSearch = edtSearch.getText().toString();
                searchAdapter.getFilter().filter(tvSearch);
            }
        });
    }

    private ListenerAPI listenerAPI = new ListenerAPI() {
        @Override
        public void onStarts() {
            activity.showDialogLoading();
        }

        @Override
        public void onResult(boolean isSuccess) {
            vegetableEntityArrayList = new ArrayList<>();

            vegetableEntityArrayList.addAll(DatabaseHelper.getDatabaseHelper(getContext()).getAllProducts());
            Log.e(TAG, "showDataVegetable: " + vegetableEntityArrayList.size());
            searchAdapter = new SearchAdapter(vegetableEntityArrayList, getContext(), activity.getSizeWithScale(302),
                    activity.getSizeWithScale(72), activity.getSizeWithScale(89), activity.getSizeWithScale(26));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            rcSearch.setAdapter(searchAdapter);
            rcSearch.setLayoutManager(linearLayoutManager);
            activity.dismissDialog();


        }
    };

    private void showDataProduct() {
        DummyApi.getDummyApi().start(listenerAPI);
    }
}