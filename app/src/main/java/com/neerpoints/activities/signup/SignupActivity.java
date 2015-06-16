package com.neerpoints.activities.signup;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.neerpoints.rest.RestClient;
import com.neerpoints.rest.RestClientImpl;

import java.util.HashMap;
import java.util.Map;

import neerpoints.com.frontend_android.R;

public class SignupActivity extends ActionBarActivity {

    public static final String SIGNUP_PHONE = "com.neerpoints.activities.signup.SignupPhone";
    private RestClient restClientImpl = RestClientImpl.getInstance();
    private EditText etSignupPhone;
    private Button bnSignupButton;
    private TextView tvSignupMessage;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        supportRequestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_signup);
        initializeViews();
    }

    public TextView getTvSignupMessage() {
        return tvSignupMessage;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public Button getBnSignupButton() {
        return bnSignupButton;
    }

    private void initializeViews() {
        progressBar = (ProgressBar) findViewById(R.id.pb_signup);
        etSignupPhone = (EditText) findViewById(R.id.et_signup_phone);
        bnSignupButton = (Button) findViewById((R.id.bn_signup_button));
        tvSignupMessage = (TextView) findViewById(R.id.tv_signup_message);
        bnSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void signup() {
        tvSignupMessage.setText("");
        Map<String, String> params = new HashMap<>();
        params.put("phone", etSignupPhone.getText().toString());
        SignupApiAdapter signupApiAdapter = new SignupApiAdapter(this);
        restClientImpl.callApi(Request.Method.POST, "client_users/", params, signupApiAdapter, this);
    }

    @Override
    protected void onStop() {
        restClientImpl.stopService(this);
        super.onStop();
    }
}
