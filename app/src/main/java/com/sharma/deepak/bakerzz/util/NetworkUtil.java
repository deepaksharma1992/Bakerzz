package com.sharma.deepak.bakerzz.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by deepak on 29-07-2018.
 */

public class NetworkUtil {

    /**
     * @param context the context of the caller
     * @return the network connection status
     * @author deepaks
     * @date 29 july 2018
     */
    public static boolean isConnectedToNetwork(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
        return activeNetwork != null &&
                activeNetwork.isConnected();
    }
}
