package com.neerpoints.activities.login;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.neerpoints.activities.company.CompanyActivity;
import com.neerpoints.app.AppController;
import com.neerpoints.model.LoginResult;
import com.neerpoints.model.ServiceResult;
import com.neerpoints.rest.ApiAdapter;

import org.json.JSONObject;

import java.util.Map;

import neerpoints.com.frontend_android.R;

public class LoginApiAdapter extends ApiAdapter {

    private final LoginActivity loginActivity;
    private final CallTag callTag;

    public LoginApiAdapter(LoginActivity loginActivity, CallTag callTag) {
        super(loginActivity);
        this.loginActivity = loginActivity;
        this.callTag = callTag;
    }

    @Override
    public void onResponse(ServiceResult serviceResult, Map<String, String> requestParams) {
        switch (callTag) {
            case LOGIN:
                if (serviceResult.isSuccess()) {
                    AppController appController = AppController.getInstance();
                    appController.putPhoneInPreferences(requestParams.get("phone"));
                    appController.putSmsKeyInPreferences(requestParams.get("smsKey"));
                    LoginResult loginData = getLoginData(serviceResult.getObject());
                    appController.putUserIdInPreferences(loginData.getUserId());
                    appController.putApiKeyInPreferences(loginData.getApiKey());
                    Toast.makeText(context, getTranslation(R.string.welcome_to_neerpoints), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, CompanyActivity.class);
                    context.startActivity(intent);
                } else {
                    loginActivity.getTvLoginMessage().setText(serviceResult.getMessage());
                }
                break;
            case RESEND_CODE:
                Toast.makeText(context, getTranslation(R.string.password_sent), Toast.LENGTH_LONG).show();
                break;
        }

    }

    private LoginResult getLoginData(String object) {
        try {
            JSONObject jsonObject = new JSONObject(object);
            String clientUserId = jsonObject.optString("clientUserId");
            String apiKey = jsonObject.optString("apiKey");
            return new LoginResult(clientUserId, apiKey);
        } catch (Exception e) {
            Toast.makeText(loginActivity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return new LoginResult("1", "");
    }

    @Override
    public void startLoading() {
        loginActivity.getBnLoginButton().setEnabled(false);
        loginActivity.getProgressBar().setVisibility(View.VISIBLE);
        loginActivity.setSupportProgressBarIndeterminateVisibility(true);
        loginActivity.setSupportProgressBarVisibility(true);
    }

    @Override
    public void stopLoading() {
        loginActivity.getBnLoginButton().setEnabled(true);
        loginActivity.getProgressBar().setVisibility(View.INVISIBLE);
        loginActivity.setSupportProgressBarIndeterminateVisibility(false);
        loginActivity.setSupportProgressBarVisibility(false);
    }

    enum CallTag {
        LOGIN, RESEND_CODE
    }
}