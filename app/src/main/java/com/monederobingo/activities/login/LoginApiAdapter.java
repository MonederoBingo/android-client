package com.monederobingo.activities.login;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.monederobingo.activities.company.CompanyActivity;
import com.monederobingo.app.AppController;
import com.monederobingo.client.R;
import com.monederobingo.common.parsers.LoginResultParser;
import com.monederobingo.model.LoginResult;
import com.monederobingo.model.ServiceResult;
import com.monederobingo.rest.ApiAdapter;

import org.json.JSONException;

import java.util.Map;

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
                    appController.putPhoneInPreferences(requestParams.get("phoneNumber"));
                    appController.putSmsKeyInPreferences(requestParams.get("smsKey"));
                    LoginResult loginData = null;
                    try {
                        loginData = LoginResultParser.getLoginResult(serviceResult.getObject());
                    } catch (JSONException e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    appController.putUserIdInPreferences(loginData.getUserId());
                    appController.putApiKeyInPreferences(loginData.getApiKey());
                    Toast.makeText(context, getTranslation(R.string.welcome_to_monederobingo), Toast.LENGTH_LONG).show();
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
