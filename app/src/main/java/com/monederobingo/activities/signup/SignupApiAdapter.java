package com.monederobingo.activities.signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.monederobingo.activities.login.LoginActivity;
import com.monederobingo.app.AppController;
import com.monederobingo.app.NotTestable;
import com.monederobingo.model.ServiceResult;
import com.monederobingo.rest.ApiAdapter;

import java.util.Map;

public class SignupApiAdapter extends ApiAdapter {

    private final SignupActivity signupActivity;
    AppController appController = AppController.getInstance();

    public SignupApiAdapter(SignupActivity signupActivity) {
        super(signupActivity);
        this.signupActivity = signupActivity;
    }

    @Override
    public void onResponse(ServiceResult serviceResult, Map<String, String> requestParams) {
        if (serviceResult.isSuccess()) {
            appController.putPhoneInPreferences(requestParams.get("phoneNumber"));
            startSignupActivity(requestParams);
        } else {
            signupActivity.setTextToTvSignupMessage(serviceResult.getMessage());
        }
    }

    void startSignupActivity(Map<String, String> requestParams) {
        Intent intent = getIntent();
        intent.putExtra(SignupActivity.SIGNUP_PHONE, requestParams.get("phoneNumber"));
        signupActivity.startActivity(intent);
    }

    @NonNull
    @NotTestable
    Intent getIntent() {
        return new Intent(signupActivity, LoginActivity.class);
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

    void setAppController(AppController appController) {
        this.appController = appController;
    }
}
