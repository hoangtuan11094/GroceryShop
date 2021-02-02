package com.example.groceryshop.activities.activities;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.adapter.MenuAdapter;
import com.example.groceryshop.activities.conetant.PrefConstants;
import com.example.groceryshop.activities.data.DatabaseHelper;
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
import com.example.groceryshop.activities.service.TimerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.groceryshop.activities.service.TimerService.KEY_TYPE;


public class ActMain extends BaseActivity implements View.OnClickListener {
    private final String TAG = "ActMain";
    private Fragment currentFragment;
    private boolean checkLogin;
    private SharedPreferences sharedPreferences;
    private FragmentManager fragmentManager;
    private View layoutMenuBar;
    private View bgMenu;
    private TextView tvName;
    private TextView tvEmail;

    public void addFragment(Fragment f) {
        try {
            fragmentManager = getSupportFragmentManager();
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
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

    public void showFrmCart() {
        addFragment(new FrmCart());
    }

    public void showFrmProductDetails(int id) {

        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        FrmProductDetails frmProductDetails = new FrmProductDetails();
        frmProductDetails.setArguments(bundle);
        addFragment(frmProductDetails);
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

    public void showFrmCheckoutAddress() {
        addFragment(new FrmCheckoutAddress());
    }

    public void showFrmCheckoutShipping() {
        addFragment(new FrmCheckoutShipping());
    }

    public void showFrmResetPassword(String email) {
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        FrmResetPassword frmResetPassword = new FrmResetPassword();
        frmResetPassword.setArguments(bundle);
        addFragment(frmResetPassword);
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

//TODO back Fragment`
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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        dataNotification(intent);

    }

    //TODO Notification
    public static final String KEY_TEXT_REPLY = "key_text_reply";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)

    public void showNotification(String title, String message, int icon, int icClose) {

//        Intent intent = new Intent(this, ActMain.class);
//        PendingIntent pIntent = PendingIntent
//                .getActivity(this, (int) System.currentTimeMillis(), intent, 0);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
//                .setSmallIcon(icon)
//                .setContentTitle(title)
//                .setContentText(message)
//                .setFullScreenIntent(pIntent, false)
//                .setPriority(NotificationCompat.PRIORITY_LOW);
//               NotificationManager notificationManager = (NotificationManager) getSystemService(
//               Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, builder.build());
//

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentText(message);

        String replyLabel = "Enter your reply here";

        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        Intent resultIntent = new Intent(this, ActMain.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(R.drawable.ic_envelope, "REPLY", resultPendingIntent)
                .addRemoteInput(remoteInput)
                .setAllowGeneratedReplies(true)
                .build();

        builder.addAction(replyAction);

        Intent intent = new Intent(this, ActMain.class);
        intent.putExtra("notificationId", 1);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent dismissIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.addAction(icClose, "DISMISS", dismissIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        //Notification Download
        new Thread(new Runnable() {
            @Override
            public void run() {
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                NotificationCompat.Builder builder1 = new NotificationCompat.Builder(getApplicationContext(), "ID");

                int PROGRESS_MAX = 100;
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    builder1.setSmallIcon(icon)
                            .setContentTitle(title)
                            .setContentText("Download( " + i + " %)")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setProgress(PROGRESS_MAX, i, false)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    notificationManagerCompat.notify(3, builder1.build());
                }

                builder1.setContentText("Download Complete")
                        .setProgress(0, 0, false)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                notificationManagerCompat.notify(3, builder1.build());
            }
        }).start();

        //Notification Show IMG.
        NotificationCompat.Builder builderImg = new NotificationCompat.Builder(this, "ID_IMAGE")
                .setSmallIcon(icon)
                .setContentTitle("Image")
                .setContentText(message)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.img_category1))
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(this.getResources(), R.drawable.img_category1)));

        NotificationManager notificationManagerImage = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManagerImage.notify(4, builderImg.build());

        //Notification Show TEXT
        NotificationCompat.Builder builderText = new NotificationCompat.Builder(this, "ID_TEXT")
                .setSmallIcon(R.drawable.ic_envelope)
                .setContentTitle("Email")
                .setContentText(message)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.img_avatar))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Email Detail\n" +
                        "Email Detail\n Email Detail\n Email Detail\n Email Detail\n Email Detail\n Email Detail\n Email Detail"));

        NotificationManager notificationManagerText = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManagerText.notify(5, builderText.build());

        //Notification Media
        NotificationCompat.Builder builderMedia = new NotificationCompat.Builder(this, "ID_MEDIA")
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_cart)
                .addAction(R.drawable.ic_previous, "Previous", null)
                .addAction(R.drawable.ic_pause, "Pause", null)
                .addAction(R.drawable.ic_next, "Next", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1)
                        .setMediaSession(null))
                .setContentTitle("Music")
                .setContentText("Chúng Ta Của Hiện Tại");
        NotificationManager notificationManagerMedia = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManagerMedia.notify(6, builderMedia.build());

        //Notification call
        Intent fullScreenIntent = new Intent(this, ActMain.class);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builderCall =
                new NotificationCompat.Builder(this, "ID_CALL")
                        .setSmallIcon(R.drawable.ic_contuct_us_white)
                        .setContentTitle("Đang gọi")
                        .setContentText("0866654199")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_CALL)
                        .setFullScreenIntent(fullScreenPendingIntent, true);

//      NotificationManager notificationManagerCall = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//      notificationManagerCall.notify(7, builderCall.build());

//Notification group
        String GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL";

        NotificationCompat.Builder builderMessage = new NotificationCompat.Builder(this, "ID_GROUP")
                .setSmallIcon(R.drawable.ic_envelope)
                .setContentTitle("Email")
                .setContentText("Gmail")
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.img_avatar))
                .setStyle(new NotificationCompat.InboxStyle()
                        .setSummaryText("cuongnguyen05092000@gmail.com"))
                .setGroup(GROUP_KEY_WORK_EMAIL)
                .setGroupSummary(true);

        Notification newMessageNotification1 =
                new NotificationCompat.Builder(this, "ID_GROUP")
                        .setSmallIcon(R.drawable.ic_envelope)
                        .setContentTitle("Email1")
                        .setContentText("Email detail")
                        .setGroup(GROUP_KEY_WORK_EMAIL)
                        .build();

        Notification newMessageNotification2 =
                new NotificationCompat.Builder(this, "ID_GROUP")
                        .setSmallIcon(R.drawable.ic_envelope)
                        .setContentTitle("Email2")
                        .setContentText("Email detail")
                        .setGroup(GROUP_KEY_WORK_EMAIL)
                        .build();

        NotificationManagerCompat notificationManagerMess = NotificationManagerCompat.from(this);
        notificationManagerMess.notify(8, newMessageNotification1);
        notificationManagerMess.notify(9, newMessageNotification2);
        notificationManagerMess.notify(0, builderMessage.build());


    }

    public void dataNotification(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);

        if (remoteInput != null) {
            CharSequence charSequence = remoteInput.getCharSequence(KEY_TEXT_REPLY);
            if (charSequence != null) {
                String reply = charSequence.toString();

                Log.e(TAG, "dataNotification: " + reply);

                NotificationCompat.Builder repliedNotification =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.ic_envelope)
                                .setContentText("Inline Reply received");

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, repliedNotification.build());
            }
        }
    }

    public void showNotificationAlarm(String dateTime)  {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = null;
        Date date = null;
        try {
            date1 = format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "showNotificationAlarm: " +date1);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, TimerService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 10, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        Log.e(TAG, "showNotificationAlarm: " +date);
        calendar.set(Calendar.SECOND, 0);
        long timer = date1.getTime() - date.getTime();
        calendar.add(Calendar.MINUTE, (int) (timer / (60 * 1000)));
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
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