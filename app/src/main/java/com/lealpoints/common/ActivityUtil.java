package com.lealpoints.common;


import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lealpoints.activities.main.MainActivity;

import lealpoints.com.frontend_android.R;

public class ActivityUtil {
    public static void setActionBarTitle(ActionBarActivity actionBarActivity) {
        actionBarActivity.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        LinearLayout inflatedView = (LinearLayout) actionBarActivity.getLayoutInflater()
                .inflate(R.layout.title_view, null);
        TextView textView = (TextView) inflatedView.findViewById(R.id.tv_title_view_app_name);
        textView.setTypeface(Typeface.createFromAsset(actionBarActivity.getAssets(),
                "fonts/Lobster-Regular.ttf"));
        actionBarActivity.getSupportActionBar().setCustomView(inflatedView,
                new android.support.v7.app.ActionBar.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public static void goToMain(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        context.startActivity(intent);
    }
}
