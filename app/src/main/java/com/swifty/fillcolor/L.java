package com.swifty.fillcolor;

import android.util.Log;

/**
 * Created by Swifty.Wang on 2015/7/31.
 */
public class L {
    private static String NAME = "fillcolor";

    public static void e(String title, String s) {
        Log.e(title, s);
    }

    public static void e(String s) {
        Log.e(NAME, s);
    }
}
