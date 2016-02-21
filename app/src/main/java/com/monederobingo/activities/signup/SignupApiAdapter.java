package com.monederobingo.activities.signup;

import android.content.Intent;
import android.view.View;

import com.monederobingo.activities.login.LoginActivity;
import com.monederobingo.app.AppController;
import com.monederobingo.model.ServiceResult;
import com.monederobingo.rest.ApiAdapter;

import java.util.Map;

public class SignupApiAdapter extends ApiAdapter {

    private final SignupActivity signupActivity;

    public SignupApiAdapter(SignupActivity signupActivity) {
        super(signupActivity);
        this.signupActivity = signupActivity;
    }


    @Override
    public void onResponse(ServiceResult serviceResult, Map<String, String> requestParams) {
        if (serviceResult.isSuccess()) {
            AppController appController = AppController.getInstance();
            appController.putPhoneInPreferences(requestParams.get("phoneNumber"));
            Intent intent = new Intent(signupActivity, LoginActivity.class);
            intent.putExtra(SignupActivity.SIGNUP_PHONE, requestParams.get("phoneNumber"));
            signupActivity.startActivity(intent);
        } else {
            signupActivity.getTvSignupMessage().setText(serviceResult.getMessage());
        }
    }

    @Override
    public void startLoading() {
        signupActivity.getBnSignupButton().setEnabled(false);
        signupActivity.getProgressBar().setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        signupActivity.getBnSignupButton().setEnabled(true);
        signupActivity.getProgressBar().setVisibility(View.INVISIBLE);
    }
}
