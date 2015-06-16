package com.neerpoints.activities.main;

import android.content.Intent;

import com.android.volley.VolleyError;
import com.neerpoints.activities.company.CompanyActivity;
import com.neerpoints.activities.login.LoginActivity;
import com.neerpoints.activities.signup.SignupActivity;
import com.neerpoints.model.ServiceResult;
import com.neerpoints.rest.ApiAdapter;

import java.util.Map;

public class MainApiAdapter extends ApiAdapter {

    public MainApiAdapter(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onResponse(ServiceResult serviceResult, Map<String, String> requestParams) {
                Intent intent = new Intent(context, CompanyActivity.class);
                context.startActivity(intent);
    }

    @Override
    public void onError(VolleyError volleyError) {
        super.onError(volleyError);
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
