package com.bellkung.espresso.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by BellKunG on 22/10/2017 AD.
 */

public class CommonSharePreference {
    public static final String NAME = "UserInfoApp";
    private SharedPreferences sharedPreferences;

    public CommonSharePreference(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void save(String key, Object data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String string = gson.toJson(data);
        editor.putString(key, string);
        editor.apply();
    }

    public Object read(String key, Class c) {
        Object result = null;
        Gson gson = new Gson();
        String string = sharedPreferences.getString(key, null);
        if (string != null) {
            result = gson.fromJson(string, c);
        }
        return result;
    }
}
