package com.monederobingo.activities.main;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.monederobingo.activities.company.CompanyActivity;
import com.monederobingo.app.AppController;
import com.monederobingo.common.ActivityUtil;
import com.monederobingo.common.parsers.LoginResultParser;
import com.monederobingo.factories.JSONObjectFactory;
import com.monederobingo.model.LoginResult;
import com.monederobingo.model.ServiceResult;
import com.monederobingo.rest.ApiAdapter;

import org.json.JSONException;

import java.util.Map;

public class MainApiAdapter extends ApiAdapter {

    private final Context context;

    public MainApiAdapter(MainActivity mainActivity) {
        super(mainActivity);
        context = mainActivity;
    }

    @Override
    public void onResponse(ServiceResult serviceResult, Map<String, String> requestParams) {
        LoginResult loginData = null;
        try {
            LoginResultParser loginResultParser = new LoginResultParser(new JSONObjectFactory());
            loginData = loginResultParser.getLoginResult(serviceResult.getObject());
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
        new ActivityUtil(context).exit();
    }
}
