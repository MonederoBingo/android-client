package com.monederobingo.activities.signup;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.monederobingo.activities.login.LoginActivity;
import com.monederobingo.app.AppController;
import com.monederobingo.app.NotTestable;
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
            getAppController().putPhoneInPreferences(requestParams.get("phoneNumber"));
            startSignupActivity(requestParams);
        } else {
            signupActivity.setTextToTvSignupMessage(serviceResult.getMessage());
        }
    }

    void startSignupActivity(Map<String, String> requestParams) {
        Intent intent = createIntent();
        intent.putExtra(SignupActivity.SIGNUP_PHONE, requestParams.get("phoneNumber"));
        signupActivity.startActivity(intent);
    }

    @NonNull
    @NotTestable
    Intent createIntent() {
        return new Intent(signupActivity, LoginActivity.class);
    }

    @NonNull
    @NotTestable
    AppController getAppController() {
        return AppController.getInstance();
    }

    @Override
    public void startLoading() {
        signupActivity.disableSignUpButton();
        signupActivity.setProgressBarVisible();
    }

    @Override
    public void stopLoading() {
        signupActivity.enableSignUpButton();
        signupActivity.setProgressBarInvisible();
    }
}
