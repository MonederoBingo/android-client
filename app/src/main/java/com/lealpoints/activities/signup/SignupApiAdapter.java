package com.lealpoints.activities.signup;

import android.content.Intent;
import android.view.View;

import com.lealpoints.activities.login.LoginActivity;
import com.lealpoints.app.AppController;
import com.lealpoints.model.ServiceResult;
import com.lealpoints.rest.ApiAdapter;

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
        signupActivity.setSupportProgressBarIndeterminateVisibility(true);
        signupActivity.setSupportProgressBarVisibility(true);
    }

    @Override
    public void stopLoading() {
        signupActivity.getBnSignupButton().setEnabled(true);
        signupActivity.getProgressBar().setVisibility(View.INVISIBLE);
        signupActivity.setSupportProgressBarIndeterminateVisibility(false);
        signupActivity.setSupportProgressBarVisibility(false);
    }
}
