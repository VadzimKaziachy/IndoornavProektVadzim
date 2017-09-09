package by.grsu.ftf.indoornav.persistantStorage;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Вадим on 08.09.2017.
 */

public abstract class Storage {
    private static final String a = "a";

    private static SharedPreferences getSharedPreferences(Context context) {
        if (context != null) {
            return context.getApplicationContext().getSharedPreferences(a, MODE_PRIVATE);
        } else return null;
    }

    public static String getMaiString(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(a, null);
        } else return null;
    }

    public static void setMai(Context context, String b) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(a, b).apply();
        }

    }

}



