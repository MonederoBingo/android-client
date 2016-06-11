package com.monederobingo.activities.signup;

import android.content.Intent;

import com.monederobingo.app.AppController;
import com.monederobingo.model.ServiceResult;

import org.junit.Before;
import org.mockito.Mock;

import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class SignupApiAdapterSpec {

    protected SignupApiAdapter signupApiAdapter;
    @Mock
    protected SignupActivity signupActivity;
    @Mock
    protected ServiceResult serviceResult;
    @Mock
    protected Intent intent;
    @Mock
    protected AppController appController;

    @Before
    public void baseSetUp() {
        signupApiAdapter = spy(new SignupApiAdapter(signupActivity));
        given(signupApiAdapter.createIntent()).willReturn(intent);
        given(signupApiAdapter.getAppController()).willReturn(appController);
    }

    protected void shouldCallCreateIntent() {
        verify(signupApiAdapter).createIntent();
    }

    protected void shouldPutExtra(String key, String phoneNumber) {
        verify(intent).putExtra(key, phoneNumber);
    }

    protected void shouldStartActivity() {
        verify(signupActivity).startActivity(intent);
    }

    protected void shouldStartSignupActivity(Map<String, String> params) {
        verify(signupApiAdapter).startSignupActivity(params);
    }

    protected void shouldNotStartSignupActivity(Map<String, String> params) {
        verify(signupApiAdapter, never()).startSignupActivity(params);
    }

    protected void shouldSetTextToTvSignupMessage(String message) {
        verify(signupActivity).setTextToTvSignupMessage(message);
    }

    protected void shouldNotSetTextToTvSignupMessage(String message) {
        verify(signupActivity, never()).setTextToTvSignupMessage(message);
    }

    protected void shouldPutPhoneInPreferences(String phoneNumber) {
        verify(appController).putPhoneInPreferences(phoneNumber);
    }

    protected void shouldNotPutPhoneInPreferences(String phoneNumber) {
        verify(appController, never()).putPhoneInPreferences(phoneNumber);
    }

    protected void shouldEnableSignUpButton() {
        verify(signupActivity).enableSignUpButton();
    }

    protected void shouldDisableSignUpButton() {
        verify(signupActivity).disableSignUpButton();
    }

    protected void shouldSetProgressBarVisible() {
        verify(signupActivity).setProgressBarVisible();
    }

    protected void shouldSetProgressBarInvisible() {
        verify(signupActivity).setProgressBarInvisible();
    }
}