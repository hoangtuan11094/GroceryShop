package com.example.groceryshop.activities.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.adapter.SlideHomeAdapter;
import com.example.groceryshop.activities.adapter.VegetableAdapter;
import com.example.groceryshop.activities.data.DatabaseHelper;
import com.example.groceryshop.activities.entity.CartEntity;
import com.example.groceryshop.activities.entity.VegetableEntity;
import com.example.groceryshop.activities.listener.ListenerAPI;
import com.example.groceryshop.activities.network.DummyApi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class FrmHome extends BaseFragment implements View.OnClickListener {

    private int[] intsImg = {R.drawable.img_viewpage1, R.drawable.img_viewpage2, R.drawable.img_viewpage3};
    private ViewPager vpHeaderHome;
    private LinearLayout indicator;
    private RecyclerView rcVegetable;
    private ArrayList<VegetableEntity> vegetableEntityArrayList;
    private VegetableAdapter vegetableAdapter;
    private TextView tvQuantityCart;
    private TextView tvTime;
    private int hour;
    private int mMinute;
    private int second;
    private int mDay;
    private int mMonth;
    private int mYear;
    private String dateTime;
    private Button btnHenGio;

    @Override
    protected int getLayoutResId() {
        return R.layout.frm_home;
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

        View imgSearch = view.findViewById(R.id.imgSearch);
        imgSearch.getLayoutParams().width = activity.getSizeWithScale(19);
        imgSearch.getLayoutParams().height = activity.getSizeWithScale(19);

        View imgCart = view.findViewById(R.id.imgCart);
        imgCart.getLayoutParams().width = activity.getSizeWithScale(24);
        imgCart.getLayoutParams().height = activity.getSizeWithScale(24);

        View clViewPage = view.findViewById(R.id.clViewPage);
        clViewPage.getLayoutParams().height = activity.getSizeWithScale(171);

        View clFooter = view.findViewById(R.id.clFooter);
        clFooter.getLayoutParams().height = activity.getSizeWithScale(60);

        View clFruits = view.findViewById(R.id.clFruits);
        clFruits.getLayoutParams().width = activity.getSizeWithScale(98);
        clFruits.getLayoutParams().height = activity.getSizeWithScale(34);

        View clBreakfast = view.findViewById(R.id.clBreakfast);
        clBreakfast.getLayoutParams().width = activity.getSizeWithScale(98);
        clBreakfast.getLayoutParams().height = activity.getSizeWithScale(34);

        View clBeverages = view.findViewById(R.id.clBeverages);
        clBeverages.getLayoutParams().width = activity.getSizeWithScale(98);
        clBeverages.getLayoutParams().height = activity.getSizeWithScale(34);

        rcVegetable = view.findViewById(R.id.rcVegetable);
        indicator = view.findViewById(R.id.indicator);
        vpHeaderHome = view.findViewById(R.id.vpHeaderHome);

        tvQuantityCart = view.findViewById(R.id.tvQuantityCart);
        tvQuantityCart.setText(activity.getTvSizeCart());

        tvTime = view.findViewById(R.id.tvTime);
         btnHenGio = view.findViewById(R.id.btnHenGio);

        imgMenu.setOnClickListener(this);
        imgSearch.setOnClickListener(this);
        imgCart.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        btnHenGio.setOnClickListener(this);

        view.findViewById(R.id.tvViewAllCategories).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        showDataVegetable();
        SlideHomeAdapter slideHomeAdapter = new SlideHomeAdapter(intsImg);
        vpHeaderHome.setAdapter(slideHomeAdapter);

        for (int i = 0; i < intsImg.length; i++) {
            View dot = createDot(indicator.getContext(), i == 0 ? Color.YELLOW : Color.WHITE);
            indicator.addView(dot);
        }

        vpHeaderHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < intsImg.length; i++) {
                    indicator.getChildAt(i).getBackground().mutate().setTint(i == position ? Color.YELLOW : Color.WHITE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        super.onViewCreated(view, savedInstanceState);


        if (vegetableEntityArrayList == null) {
            vegetableEntityArrayList = new ArrayList<>();
            vegetableEntityArrayList.addAll(DatabaseHelper.getDatabaseHelper(getContext()).getAllProducts());
        }
        vegetableAdapter = new VegetableAdapter(vegetableEntityArrayList, getContext(), activity.getSizeWithScale(146),
                activity.getSizeWithScale(167), activity.getSizeWithScale(134), activity.getSizeWithScale(78), new VegetableAdapter.OnClickItemListener() {
            @Override
            public void onClicked(int position) {
                DatabaseHelper.getDatabaseHelper(getContext()).insertCart(new CartEntity(vegetableEntityArrayList.get(position).getImgProduct(), vegetableEntityArrayList.get(position).getProductName(), vegetableEntityArrayList.get(position).getProductPrice(), 1));
                tvQuantityCart.setText(activity.getTvSizeCart());
                activity.showNotification(TAG, "Add to cart successfully", R.drawable.icon_send, R.drawable.ic_close);
            }

            @Override
            public void onClickedItem(int position) {
                activity.showFrmProductDetails(vegetableEntityArrayList.get(position).getIdProduct());
            }

        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcVegetable.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvViewAllCategories:
                activity.showFrmCategory();
                break;
            case R.id.imgMenu:
                activity.showMenu();
                break;
            case R.id.imgSearch:
                activity.showFrmSearch();
                break;
            case R.id.imgCart:
                activity.showFrmCart();
                break;
            case R.id.tvTime:

                break;
            case R.id.btnHenGio:
                Log.e(TAG, "onClick: " + dateTime );
                showDatePicker();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    View createDot(Context context, @ColorInt int color) {
        View dot = new View(context);
        ViewGroup.MarginLayoutParams dotParams = new ViewGroup.MarginLayoutParams(20, 20);
        dotParams.setMargins(20, 10, 20, 10);
        dot.setLayoutParams(dotParams);
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.setTint(color);
        dot.setBackground(drawable);
        return dot;
    }

    //TODO Dummy API
    private ListenerAPI listenerAPI = new ListenerAPI() {
        @Override
        public void onStarts() {
            activity.showDialogLoading();
        }

        @Override
        public void onResult(boolean isSuccess) {
            try {
                rcVegetable.setAdapter(vegetableAdapter);
                activity.dismissDialog();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    };

    private void showDatePicker() {
        try {
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute = calendar.get(Calendar.MINUTE);
                second = calendar.get(Calendar.SECOND);
                mDay = calendar.get(Calendar.DATE);
                mMonth = calendar.get(Calendar.MONTH) + 1;
                mYear = calendar.get(Calendar.YEAR);
                dateTime = mYear+"-"+mMonth+"-"+mDay+" "+hour+":"+mMinute;
                try {
                    new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            try {
                                if (year != mYear
                                        || month + 1 != mMonth
                                        || dayOfMonth != mDay) {
                                    mYear = year;
                                    mMonth = month + 1;
                                    mDay = dayOfMonth;
                                    dateTime = mYear+"-"+mMonth+"-"+mDay+" "+hour+":"+mMinute;
                                }
                    new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            try {
                                if (hourOfDay != hour
                                        || minute != mMinute) {
                                    hour = hourOfDay;
                                    mMinute = minute;
                                    dateTime = mYear+"-"+mMonth+"-"+mDay+" "+hour+":"+mMinute;
                                    activity.showNotificationAlarm(dateTime);
                                    btnHenGio.setTextColor(getResources().getColor(R.color.colorRed));
                                    btnHenGio.setBackgroundResource(R.drawable.bg_btn_yellow);
                                }
                            } catch (Throwable e) {
                                e.printStackTrace();
                            }

                        }
                    }, hour, mMinute, true).show();
                            } catch (Throwable e) {
                                e.printStackTrace();
                            }
                        }

                    }, mYear, mMonth - 1, mDay).show();
                } catch (Throwable e) {
                    e.printStackTrace();
                }

        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private void showDataVegetable() {
        try {
            if (activity.isConnected()) {
                DummyApi.getDummyApi().start(listenerAPI);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

}