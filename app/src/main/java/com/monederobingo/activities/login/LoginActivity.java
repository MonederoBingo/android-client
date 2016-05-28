package com.monederobingo.activities.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.monederobingo.activities.signup.SignupActivity;
import com.monederobingo.app.AppController;
import com.monederobingo.client.R;
import com.monederobingo.common.ActivityUtil;
import com.monederobingo.rest.RestClient;
import com.monederobingo.rest.RestClientImpl;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends Activity {

    Button bnLoginButton;
    private RestClient restClientImpl = RestClientImpl.getInstance();
    private TextView tvLoginPhone;
    private EditText etLoginSmsKey;
    private TextView tvLoginMessage;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
    }

    public TextView getTvLoginMessage() {
        return tvLoginMessage;
    }

    public Button getBnLoginButton() {
        return bnLoginButton;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    private void initializeViews() {
        Intent intent = getIntent();
        progressBar = (ProgressBar) findViewById(R.id.pb_login);
        tvLoginPhone = (TextView) findViewById(R.id.tv_login_phone);
        etLoginSmsKey = (EditText) findViewById(R.id.et_login_sms_key);
        tvLoginMessage = (TextView) findViewById(R.id.tv_login_message);
        bnLoginButton = (Button) findViewById((R.id.bn_login_button));
        bnLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        String phone = intent.getStringExtra(SignupActivity.SIGNUP_PHONE);
        tvLoginPhone.setText(phone);
        etLoginSmsKey.requestFocus();
    }

    private void resendKey() {
        final Map<String, String> params = new HashMap<>();
        params.put("phoneNumber", tvLoginPhone.getText().toString());
        LoginApiAdapter apiAdapter = new LoginApiAdapter(this, LoginApiAdapter.CallTag.RESEND_CODE);
        restClientImpl.callAuth(Request.Method.POST, "client/resend_key", params, apiAdapter, this.getLocalClassName());
    }

    private void loginWithDifferentPhoneNumber() {
        AppController appController = AppController.getInstance();
        appController.removePhoneFromPreferences();
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    private void login() {
        final Map<String, String> params = new HashMap<>();
        params.put("phoneNumber", tvLoginPhone.getText().toString());
        params.put("smsKey", etLoginSmsKey.getText().toString());
        LoginApiAdapter apiAdapter = new LoginApiAdapter(this, LoginApiAdapter.CallTag.LOGIN);
        restClientImpl.callAuth(Request.Method.POST, "client/login", params, apiAdapter, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_login_resend_key:
                resendKey();
                return true;
            case R.id.action_login_with_different_phone_number:
                loginWithDifferentPhoneNumber();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
