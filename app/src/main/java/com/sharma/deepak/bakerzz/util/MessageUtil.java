package com.sharma.deepak.bakerzz.util;

import android.content.Context;
import android.widget.Toast;

public class MessageUtil {

    /**
     * @param context the context object
     * @param message the message string to show on toast
     * @author deepaks
     * @date 29 july 2018
     * @descripton method to show the toast message
     */
    public static void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
