package com.rahul.getup;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rahul on 24/12/14.
 */
public class PreferenceManager {
    private static final String PREF_NAME = "com.rahul.getup.prefs";

    static int getDiffTime(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        return prefs.getInt("diffTime", 60);
    }

    static void setDiffTime(Context context, int diffTime) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE).edit();
        editor.putInt("diffTime", diffTime);
        editor.commit();
    }

    static boolean getEnabledStatus(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        return prefs.getBoolean("enabledStatus", false);
    }

    static void setEnabledStatus(Context context, boolean enabledStatus) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE).edit();
        editor.putBoolean("enabledStatus", enabledStatus);
        editor.commit();
    }
}
