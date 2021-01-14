package com.example.groceryshop.activities.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.groceryshop.activities.conetant.PrefConstants;

public class PrefNetword {
    private SharedPreferences sharedPreferences;
    private static PrefNetword prefNetword;

    private PrefNetword() {
    }

    public static PrefNetword getPrefNetword() {
        if (prefNetword == null) {
            prefNetword = new PrefNetword();
        }
        return prefNetword;
    }
    public SharedPreferences newSharedPreferences (Context context){
        if (sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(PrefConstants.PREF_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }
}
