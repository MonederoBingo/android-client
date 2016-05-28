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
//        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
//        supportRequestWindowFeature(Window.FEATURE_PROGRESS);
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
                signUp();
            }
        });
        //ActivityUtil.setActionBarTitle(this);
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
