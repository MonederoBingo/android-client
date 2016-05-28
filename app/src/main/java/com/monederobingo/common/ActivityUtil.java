package com.monederobingo.common;


import android.content.Context;
import android.content.Intent;

import com.monederobingo.activities.main.MainActivity;

public class ActivityUtil {

    private Intent intent;
    private Context context;

    public ActivityUtil(Context context) {
        this.context = context;
        intent = new Intent(context, MainActivity.class);
    }

    public void exit() {
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        context.startActivity(intent);
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
