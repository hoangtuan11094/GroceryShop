package com.example.groceryshop.activities.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.adapter.MenuAdapter;
import com.example.groceryshop.activities.conetant.PrefConstants;
import com.example.groceryshop.activities.data.DatabaseHelper;
import com.example.groceryshop.activities.entity.CartEntity;
import com.example.groceryshop.activities.entity.MenuEntity;
import com.example.groceryshop.activities.entity.UserEntity;
import com.example.groceryshop.activities.fragment.FrmCart;
import com.example.groceryshop.activities.fragment.FrmCategory;
import com.example.groceryshop.activities.fragment.FrmCheckoutAddress;
import com.example.groceryshop.activities.fragment.FrmCheckoutShipping;
import com.example.groceryshop.activities.fragment.FrmForgotPassword;
import com.example.groceryshop.activities.fragment.FrmHome;
import com.example.groceryshop.activities.fragment.FrmLogin;
import com.example.groceryshop.activities.fragment.FrmOrderSummary;
import com.example.groceryshop.activities.fragment.FrmProductDetails;
import com.example.groceryshop.activities.fragment.FrmResetPassword;
import com.example.groceryshop.activities.fragment.FrmSearchProduct;
import com.example.groceryshop.activities.fragment.FrmSignUp;
import com.example.groceryshop.activities.fragment.FrmWelcome;
import com.example.groceryshop.activities.listener.ListenerAPI;
import com.example.groceryshop.activities.network.DummyApi;
import com.example.groceryshop.activities.network.Preferences;

import java.util.ArrayList;
import java.util.List;

public class ActMain extends BaseActivity implements View.OnClickListener {
    private final String TAG = "ActMain";
    private Fragment currentFragment;
    private boolean checkLogin;
    private SharedPreferences sharedPreferences;
    private View layoutMenuBar;
    private View bgMenu;
    private TextView tvName;
    private TextView tvEmail;

    public void addFragment(Fragment f) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            currentFragment = fragmentManager.findFragmentById(R.id.frameMenuContainer);
            if (currentFragment != null) {
                fragmentManager.beginTransaction()
                        .remove(currentFragment)
                        .commitAllowingStateLoss();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        addOrReplaceFragment(R.id.frameParent, f);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDialogLoading();
        DatabaseHelper.getDatabaseHelper(this).createDataBase();
        sharedPreferences = Preferences.getPreferences().newSharedPreferences(this);
        checkLogin = sharedPreferences.getBoolean(PrefConstants.PREF_CHECK_LOGIN, false);
        navigationApp();
        customMenu();
    }

    private void navigationApp() {
        if (checkLogin) {
            addFragment(new FrmHome());
        } else
            addFragment(new FrmWelcome());
    }


    //TODO show Fragment
    public void showFrmLogin() {
        addFragment(new FrmLogin());
    }

    public void showFrmSignUp() {
        addFragment(new FrmSignUp());
    }

    public void showFrmCategory() {
        addFragment(new FrmCategory());
    }

    public void showFrmHome() {
        addFragment(new FrmHome());
    }

    public void showFrmCart(){
        addFragment(new FrmCart());
    }

    public void showFrmProductDetails() {
        addFragment(new FrmProductDetails());
    }

    public void showFrmSearch() {
        addFragment(new FrmSearchProduct());
    }

    public void showFrmOrderSummary() {
        addFragment(new FrmOrderSummary());
    }

    public void showFrmForgotPassword() {
        addFragment(new FrmForgotPassword());
    }

    public void showFrmCheckoutAddress(){
        addFragment(new FrmCheckoutAddress());
    }

    public void showFrmCheckoutShipping(){
        addFragment(new FrmCheckoutShipping());
    }

    public void showFrmResetPassword(String email) {
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        FrmResetPassword frmResetPassword = new FrmResetPassword();
        frmResetPassword.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft_add = fm.beginTransaction();
        ft_add.add(R.id.frameParent, frmResetPassword);
        ft_add.commit();

    }

    public void logout() {
        DummyApi.getDummyApi().start(listenerAPI);
    }

    ListenerAPI listenerAPI = new ListenerAPI() {
        @Override
        public void onStarts() {
            showDialogLoading();
        }

        @Override
        public void onResult(boolean isSuccess) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(PrefConstants.PREF_CHECK_LOGIN, false);
            editor.commit();
            showFrmLogin();
            dismissDialog();
        }
    };

    //TODO menu bar
    private void dataListMenu() {
        RecyclerView rcMenuBar;
        rcMenuBar = findViewById(R.id.rcMenu);
        List<MenuEntity> menuEntityList = new ArrayList<>();
        menuEntityList.add(new MenuEntity(R.drawable.ic_home_white, R.drawable.ic_home_yellow, "Home"));
        menuEntityList.add(new MenuEntity(R.drawable.ic_my_order_white, R.drawable.ic_my_order_yellow, "My Order"));
        menuEntityList.add(new MenuEntity(R.drawable.ic_profile_white, R.drawable.ic_profile_yellow, "Profile"));
        menuEntityList.add(new MenuEntity(R.drawable.ic_faq_white, R.drawable.ic_faq_yellow, "FAQ"));
        menuEntityList.add(new MenuEntity(R.drawable.ic_policy_white, R.drawable.ic_policy_yellow, "Privacy Policy"));
        menuEntityList.add(new MenuEntity(R.drawable.ic_setting_white, R.drawable.ic_setting_yellow, "Settings"));
        menuEntityList.add(new MenuEntity(R.drawable.ic_contuct_us_white, R.drawable.ic_contuct_us_yellow, "Contact us"));

        MenuAdapter menuAdapter = new MenuAdapter(menuEntityList, this, getSizeWithScale(245), getSizeWithScale(27), getSizeWithScale(14),
                getSizeWithScale(14), getSizeWithScale(7), getSizeWithScale(11), new MenuAdapter.OnClickItemListener() {
            @Override
            public void onClicked(int position) {
                showFragmentMenu(position);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcMenuBar.setAdapter(menuAdapter);
        rcMenuBar.setLayoutManager(linearLayoutManager);
    }

    private void getSizeMenu() {
        layoutMenuBar = findViewById(R.id.layoutMenuBar);
        View clMenuBar = findViewById(R.id.clMenuBar);
        clMenuBar.getLayoutParams().width = getSizeWithScale(318);
        View imgBgMenuBar = findViewById(R.id.imgBgMenuBar);
        imgBgMenuBar.getLayoutParams().width = getSizeWithScale(318);
        imgBgMenuBar.getLayoutParams().height = getSizeWithScale(667);

        View imgAvatar = findViewById(R.id.imgAvatar);
        imgAvatar.getLayoutParams().width = getSizeWithScale(126);
        imgAvatar.getLayoutParams().height = getSizeWithScale(126);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        bgMenu = findViewById(R.id.imgMenu);

    }

    private void getInformationUser() {
        UserEntity userEntity = new UserEntity();
        String email = sharedPreferences.getString(PrefConstants.PREF_EMAIL_SAVE_USER, "");
        userEntity = DatabaseHelper.getDatabaseHelper(this).getUserLogin(email);
        Log.e(TAG, "customMenu: " + email);
        Log.e(TAG, "customMenu: " + userEntity.email);

        tvName.setText(userEntity.fullName);
        tvEmail.setText(userEntity.email);
    }

    private void customMenu() {
        getSizeMenu();
        getInformationUser();
        dataListMenu();
        findViewById(R.id.clLogout).setOnClickListener(this);

    }

    private void showFragmentMenu(int position) {
        switch (position) {
            case 0:
                showFrmHome();
                layoutMenuBar.setVisibility(View.GONE);
                break;
            case 1:
                showFrmCategory();
                layoutMenuBar.setVisibility(View.GONE);
                break;
        }
    }

    public void showMenu() {
        layoutMenuBar.setVisibility(View.VISIBLE);

    }

    //TODO data cart


    public String getTvSizeCart() {
        if (DatabaseHelper.getDatabaseHelper(this).getAllCart() == null)
            return "0";
        else
            return String.valueOf(DatabaseHelper.getDatabaseHelper(this).getAllCart().size());
    }


    public void deleteItemCart(int position){
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clLogout:
                logout();
                layoutMenuBar.setVisibility(View.GONE);
                break;
        }
    }

    //TODO size manager
    private float scaleValue = 0;
    private DisplayMetrics displayMetrics;

    private DisplayMetrics getDisplayMetrics() {
        if (displayMetrics == null)
            displayMetrics = getResources().getDisplayMetrics();
        return displayMetrics;
    }


    private int screenWidth = 0;

    public int getScreenWidth() {
        if (screenWidth == 0)
            screenWidth = getDisplayMetrics().widthPixels;
        return screenWidth;
    }

    private float getScaleValue() {
        if (scaleValue == 0)
            scaleValue = getScreenWidth() * 1f / 375;
        return scaleValue;
    }

    public int getSizeWithScale(double sizeDesign) {
        return (int) (sizeDesign * getScaleValue());
    }

}