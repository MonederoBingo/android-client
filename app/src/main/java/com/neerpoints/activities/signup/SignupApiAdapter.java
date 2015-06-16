package com.neerpoints.activities.signup;

import android.content.Intent;
import android.view.View;

import com.neerpoints.activities.login.LoginActivity;
import com.neerpoints.app.AppController;
import com.neerpoints.model.ServiceResult;
import com.neerpoints.rest.ApiAdapter;

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
            appController.putPhoneInPreferences(requestParams.get("phone"));
            Intent intent = new Intent(signupActivity, LoginActivity.class);
            intent.putExtra(SignupActivity.SIGNUP_PHONE, requestParams.get("phone"));
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
