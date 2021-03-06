package com.example.groceryshop.activities.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.adapter.SlideHomeAdapter;

import java.util.Collection;

public class FrmHome extends BaseFragment implements View.OnClickListener {

    private int[] intsImg = {R.drawable.img_viewpage1, R.drawable.img_viewpage2, R.drawable.img_viewpage3};
    private ViewPager vpHeaderHome;
    private LinearLayout indicator;
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

        View clFood = view.findViewById(R.id.clFood);
        clFood.getLayoutParams().height = activity.getSizeWithScale(60);

        View clFruits = view.findViewById(R.id.clFruits);
        clFruits.getLayoutParams().width = activity.getSizeWithScale(98);
        clFruits.getLayoutParams().height = activity.getSizeWithScale(34);

        View clBreakfast = view.findViewById(R.id.clBreakfast);
        clBreakfast.getLayoutParams().width = activity.getSizeWithScale(98);
        clBreakfast.getLayoutParams().height = activity.getSizeWithScale(34);

        View clBeverages = view.findViewById(R.id.clBeverages);
        clBeverages.getLayoutParams().width = activity.getSizeWithScale(98);
        clBeverages.getLayoutParams().height = activity.getSizeWithScale(34);

        indicator = view.findViewById(R.id.indicator);
        vpHeaderHome = view.findViewById(R.id.vpHeaderHome);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
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
    }

    @Override
    public void onClick(View v) {

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
}