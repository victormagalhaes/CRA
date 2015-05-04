package com.example.victor.cra.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesHelper {
    private SharedPreferences sharedPreferences = null;

    public SharedPreferencesHelper(Activity activity) {
        this.sharedPreferences = activity.getApplicationContext().getSharedPreferences(null, Context.MODE_PRIVATE);
    }

    public SharedPreferencesHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences(null, Context.MODE_PRIVATE);
    }

    private boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void remove(String key) {
        Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public void set(String key, String value) {
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void set(String key, boolean value) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}
