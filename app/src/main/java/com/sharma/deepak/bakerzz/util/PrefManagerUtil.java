package com.sharma.deepak.bakerzz.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.sharma.deepak.bakerzz.widget.PreferenceTransactions;

/**
 * created by deepaks on 28 feb 2018.
 * description class for saving the preference value
 */
public class PrefManagerUtil {

    private static final SharedPreferences sharedPreferences = BakerApplication
            .getAppContext()
            .getSharedPreferences(GlobalConstants.BAKER_PREFERENCE
                    , Context.MODE_PRIVATE);

    /**
     * @param key the key for shared preference
     * @return the value of preference
     * @author deeapks
     * @date 28 feb 2018
     * @description method returns the value for shared preference
     */
    public static String getString(String key) {
        return sharedPreferences.getString(key, GlobalConstants.BLANK);
    }

    /**
     * @param keyPreference   the key for shared preference
     * @param valuePreference the value for string preference
     * @author deeapks
     * @date 14 august 2018
     * @description method sets the value for shared preference
     */
    public static void setString(String keyPreference, String valuePreference) {
        PreferenceTransactions.setStringPreference(keyPreference, valuePreference);
    }

    public static void clearAllSharedPreferences() {
        sharedPreferences.edit().clear().apply();
    }

}
