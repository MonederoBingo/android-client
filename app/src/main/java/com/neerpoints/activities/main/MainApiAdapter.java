package com.neerpoints.activities.main;

import android.content.Intent;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.neerpoints.activities.company.CompanyActivity;
import com.neerpoints.activities.login.LoginActivity;
import com.neerpoints.app.AppController;
import com.neerpoints.common.parsers.LoginResultParser;
import com.neerpoints.model.LoginResult;
import com.neerpoints.model.ServiceResult;
import com.neerpoints.rest.ApiAdapter;

import org.json.JSONException;

import java.util.Map;

public class MainApiAdapter extends ApiAdapter {

    public MainApiAdapter(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onResponse(ServiceResult serviceResult, Map<String, String> requestParams) {
        LoginResult loginData = null;
        try {
            loginData = LoginResultParser.getLoginResult(serviceResult.getObject());
        } catch (JSONException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        if (loginData != null) {
            AppController.getInstance().putApiKeyInPreferences(loginData.getApiKey());
        }
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
