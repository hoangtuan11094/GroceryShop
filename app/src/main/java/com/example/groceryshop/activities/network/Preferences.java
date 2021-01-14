package com.example.groceryshop.activities.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.groceryshop.activities.conetant.PrefConstants;

public class Preferences {
    private SharedPreferences sharedPreferences;
    private static Preferences preferences;

    private Preferences() {
    }

    public static Preferences getPreferences() {
        if (preferences == null) {
            preferences = new Preferences();
        }
        return preferences;
    }
    public SharedPreferences newSharedPreferences (Context context){
        if (sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(PrefConstants.PREF_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }
}
