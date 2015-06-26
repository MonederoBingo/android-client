package com.neerpoints.activities.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.neerpoints.activities.login.LoginActivity;
import com.neerpoints.activities.signup.SignupActivity;
import com.neerpoints.app.AppController;
import com.neerpoints.rest.RestClient;
import com.neerpoints.rest.RestClientImpl;

import java.util.HashMap;
import java.util.Map;

import neerpoints.com.frontend_android.R;


public class MainActivity extends Activity {

    Map<String, String> params = new HashMap<>();
    private RestClient restClientImpl = RestClientImpl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        } else {
            login();
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
            params.put("phone", phone);
            params.put("smsKey", smsKey);
            MainApiAdapter mainApiAdapter = new MainApiAdapter(this);
            restClientImpl.callAuth(Request.Method.POST, "client/login", params, mainApiAdapter, this.getLocalClassName());
        }
    }
}
