package by.grsu.ftf.indoornav.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Вадим on 08.09.2017.
 */

public  class Storage {
    private static final String KEY_SETTINGS = "KEY_SETTINGS";
    private static final String a = "a";

    private static SharedPreferences getSharedPreferences(Context context) {
        if (context != null) {
            return context.getApplicationContext().getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE);
        } else return null;

    }

    public static String getRepository(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(a, null);
        } else return null;
    }

    public static void setRepository(Context context, String b) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(a, b).apply();

        }
    }
}



