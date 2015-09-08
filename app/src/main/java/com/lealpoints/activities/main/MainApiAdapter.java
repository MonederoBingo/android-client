package com.lealpoints.activities.main;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.lealpoints.activities.company.CompanyActivity;
import com.lealpoints.activities.login.LoginActivity;
import com.lealpoints.app.AppController;
import com.lealpoints.common.ActivityUtil;
import com.lealpoints.common.parsers.LoginResultParser;
import com.lealpoints.model.LoginResult;
import com.lealpoints.model.ServiceResult;
import com.lealpoints.rest.ApiAdapter;

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
        ActivityUtil.exit(context);
    }
}
