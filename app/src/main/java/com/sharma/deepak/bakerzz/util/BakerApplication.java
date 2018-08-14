package com.sharma.deepak.bakerzz.util;

import android.app.Application;
import android.content.Context;

/**
 * description class to get the application context
 */

public class BakerApplication extends Application {
    private static BakerApplication bakerApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        bakerApplication = this;
    }

    /**
     * @return the application context
     * @author deepaks
     * @date 14 august 2018
     */
    public static BakerApplication getInstance() {
        return bakerApplication;
    }

    /**
     * @return the app context
     * @author deeapaks
     * @date 14 august 2018
     */
    public static Context getAppContext() {
        return bakerApplication.getApplicationContext();
    }

}
