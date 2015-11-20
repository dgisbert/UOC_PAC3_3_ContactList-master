package edu.uoc.android.contacts.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by Marc on 23/02/2015.
 *
 * This class is useful to manage SharedPreferences
 */
public class SharePreferenceUtil {

    private static final String TAG = SharePreferenceUtil.class.getSimpleName();

    public static final String CONTACTS_LAST_UPDATE = "CONTACTS_LAST_UPDATE";

    /**
     * Method to save a Long value in a SharedPreferences.
     *
     * @param context
     * @param key
     * @param value
     * @return true if value has been saved correctly
     */
    public static boolean setLongValue(Context context, String key, Long value) {
        boolean result = false;
        try {
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = settings.edit();
            if(value!=null)
                editor.putLong(key, value);
            else
                editor.remove(key);
            editor.commit();
            result = true;
        }catch(Exception e){
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return result;
    }

    /**
     * Method to remove Long value from SharedPreferences
     *
     * @param context
     * @param key
     * @return true if value has been removed correctly
     */
    public static boolean removeLongValue(Context context, String key){
        return setLongValue(context, key, null);
    }

    /**
     * Method to get a Long value from SharedPreferences (without default value).
     *
     * @param context
     * @param key
     * @return Long value
     */
    public static long getLongValue(Context context, String key) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getLong(key, -1);
    }

    /**
     * Method to get Long value from SharedPreferences.
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return Long value
     */
    public static long getLongValue(Context context, String key, Long defaultValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getLong(key, defaultValue);
    }
}
