package com.monederobingo.activities.signup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.monederobingo.client.R;
import com.monederobingo.common.ActivityUtil;
import com.monederobingo.rest.RestClient;
import com.monederobingo.rest.RestClientImpl;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends Activity {

    public static final String SIGNUP_PHONE = "com.monederobingo.activities.signup.SignupPhone";
    private RestClient restClientImpl = RestClientImpl.getInstance();
    private EditText etSignupPhone;
    private Button bnSignupButton;
    private TextView tvSignupMessage;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initializeViews();
    }

    public void setProgressBarVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void setProgressBarInvisible() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void enableSignUpButton() {
        bnSignupButton.setEnabled(true);
    }

    public void disableSignUpButton() {
        bnSignupButton.setEnabled(false);
    }

    public void setTextToTvSignupMessage(String message) {
        tvSignupMessage.setText(message);
    }

    private void initializeViews() {
        progressBar = (ProgressBar) findViewById(R.id.pb_signup);
        etSignupPhone = (EditText) findViewById(R.id.et_signup_phone);
        bnSignupButton = (Button) findViewById((R.id.bn_signup_button));
        tvSignupMessage = (TextView) findViewById(R.id.tv_signup_message);
        bnSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        tvSignupMessage.setText("");
        Map<String, String> params = new HashMap<>();
        params.put("phoneNumber", etSignupPhone.getText().toString());
        SignupApiAdapter signupApiAdapter = new SignupApiAdapter(this);
        restClientImpl.callAuth(Request.Method.POST, "client/", params, signupApiAdapter, this);
    }

    @Override
    protected void onStop() {
        restClientImpl.stopService(this);
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        new ActivityUtil(this).exit();
    }
}
