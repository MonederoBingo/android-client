package com.lealpoints.activities.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.lealpoints.activities.login.LoginActivity;
import com.lealpoints.activities.signup.SignupActivity;
import com.lealpoints.app.AppController;
import com.lealpoints.rest.RestClient;
import com.lealpoints.rest.RestClientImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import lealpoints.com.frontend_android.R;


public class MainActivity extends Activity {

    Map<String, String> params = new HashMap<>();
    private RestClient restClientImpl = RestClientImpl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView appName = (TextView) findViewById(R.id.appName);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
        appName.setTypeface(typeface);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        } else {
            Timer timer = new Timer("login");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    login();
                }
            }, 1500);
        }
    }

    private void login() {
        AppController appController = AppController.getInstance();
        String phone = appController.getPhoneFromPreferences();
        String smsKey = appController.getSmsKeyFromPreferences();

        if (phone.equals("") || smsKey.equals("")) {
            if (phone.equals("")) {
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra(SignupActivity.SIGNUP_PHONE, phone);
                startActivity(intent);
            }
        } else {
            params.put("phoneNumber", phone);
            params.put("smsKey", smsKey);
            MainApiAdapter mainApiAdapter = new MainApiAdapter(this);
            restClientImpl.callAuth(Request.Method.POST, "client/login", params, mainApiAdapter, this.getLocalClassName());
        }
    }
}
