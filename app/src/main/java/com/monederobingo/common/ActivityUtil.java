package com.monederobingo.common;


import android.content.Context;
import android.content.Intent;

import com.monederobingo.activities.main.MainActivity;

public class ActivityUtil {
    public static void exit(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        context.startActivity(intent);
    }
}
