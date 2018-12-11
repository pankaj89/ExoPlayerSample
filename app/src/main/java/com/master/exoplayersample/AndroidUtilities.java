package com.master.exoplayersample;

import android.content.Context;

public class AndroidUtilities {
    public static float density = 1;

    public static void init(Context context) {
        density = context.getResources().getDisplayMetrics().density;
    }

    public static int dp(float value) {

        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }

    public static int dp2(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.floor(density * value);
    }
}