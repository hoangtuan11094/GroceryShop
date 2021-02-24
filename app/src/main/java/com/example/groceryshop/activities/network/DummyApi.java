package com.example.groceryshop.activities.network;

import android.os.Handler;

import com.example.groceryshop.activities.listener.ListenerAPI;

import java.util.Random;

public class DummyApi {
    private ListenerAPI mListenerAPI;
    private Handler handler;
    private static DummyApi dummyApi = new DummyApi();

    private DummyApi() {
    }

    public static DummyApi getDummyApi() {
        if (dummyApi == null){
            dummyApi = new DummyApi();
        }
        return dummyApi;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mListenerAPI != null) {
                mListenerAPI.onResult(getRandomBoolean());
            }
        }
    };

    public void start(ListenerAPI listenerAPI) {
        this.mListenerAPI = listenerAPI;
        if (handler == null) handler = new Handler();
        if (mListenerAPI != null) {
            mListenerAPI.onStarts();
        }
        handler.postDelayed(runnable, 1000L);
    }

    private boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

}
