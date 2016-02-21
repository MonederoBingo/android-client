package com.monederobingo.activities.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.monederobingo.activities.login.LoginActivity;
import com.monederobingo.activities.signup.SignupActivity;
import com.monederobingo.app.AppController;
import com.monederobingo.client.R;
import com.monederobingo.rest.RestClient;
import com.monederobingo.rest.RestClientImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    Map<String, String> params = new HashMap<>();
    private RestClient restClientImpl = RestClientImpl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
