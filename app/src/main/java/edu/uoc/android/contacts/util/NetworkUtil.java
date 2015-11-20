package edu.uoc.android.contacts.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Class offering utility methods related to network.
 */

public class NetworkUtil
{
    /**
     * Array of default sites to check http connection availability
     */

    private static final String[] URL_SITES_CHECK_CONNECTIVITY = {"http://www.google.com"};
    private static final int      TIMEOUT_CHECK_CONNECTIVITY = 1500;

    /**
     * Checks if device has network available.
     */

    private static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null) && (networkInfo.isConnectedOrConnecting());
    }

    /**
     * Checks if device has Internet connexion. Returns true if device has Internet connexion. Otherwise, return false.
     */

    public static boolean isConnectionAvailable(Context context)
    {
        boolean isAvailable = isNetworkAvailable(context);

//        if (isAvailable)
//        {
//            isAvailable = false;
//
//            for (int i = 0; (i < URL_SITES_CHECK_CONNECTIVITY.length) && !isAvailable; i++)
//            {
//                try
//                {
//
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(URL_SITES_CHECK_CONNECTIVITY[i]).openConnection();
//
//                    httpURLConnection.setRequestProperty("User-Agent", "Test");
//                    httpURLConnection.setRequestProperty("Connection", "close");
//                    httpURLConnection.setConnectTimeout(TIMEOUT_CHECK_CONNECTIVITY);
//                    httpURLConnection.connect();
//
//                    isAvailable= (httpURLConnection.getResponseCode() == 200);
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }

        return isAvailable;
    }
}
