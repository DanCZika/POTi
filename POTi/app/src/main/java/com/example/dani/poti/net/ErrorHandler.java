package com.example.dani.poti.net;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Dani on 2017. 03. 22..
 */

public class ErrorHandler {

    private static Activity activity=null;

    public static void showError(final String message)
    {
        if (activity !=null){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity,message, Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public static void setActivity(Activity activity) {ErrorHandler.activity = activity;}


}
