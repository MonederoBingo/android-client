package com.monederobingo.activities.signup;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.monederobingo.app.AppController;
import com.monederobingo.model.ServiceResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SignupApiAdapterTest_onResponse_ifTrue {

    @Mock
    private SignupActivity signupActivity;
    @Mock
    private ServiceResult serviceResult;
    @Mock
    private AppController appController;
    @Mock
    private Intent intent;
    private Map<String, String> requestParams = new HashMap<>();
    private SignupApiAdapter signupApiAdapter;
    private boolean startSignupAcitivityCalled = false;

    @Before
    public void setUp() {
        given(serviceResult.isSuccess()).willReturn(true);
        signupApiAdapter = createSignupAdapter();
        signupApiAdapter.setAppController(appController);
    }

    private SignupApiAdapter createSignupAdapter() {
        return new SignupApiAdapter(signupActivity) {
            @NonNull
            @Override
            Intent getIntent() {
                return intent;
            }

            @Override
            void startSignupActivity(Map<String, String> requestParams) {
                startSignupAcitivityCalled = true;
            }
        };
    }

    @Test
    public void shouldPutPhoneInPreferences() throws Exception {
        //given
        requestParams.put("phoneNumber", "1234567890");
        //when
        signupApiAdapter.onResponse(serviceResult, requestParams);
        //then
        verify(appController).putPhoneInPreferences("1234567890");
    }

    @Test
    public void shouldCallStartSignupActivity() throws Exception {
        //when
        signupApiAdapter.onResponse(serviceResult, requestParams);
        //then
        assertTrue(startSignupAcitivityCalled);
    }

    @Test
    public void shouldNotSetTextToTvSignupMessage() throws Exception {
        //given
        given(serviceResult.getMessage()).willReturn("message");
        //when
        signupApiAdapter.onResponse(serviceResult, requestParams);
        //then
        verify(signupActivity, never()).setTextToTvSignupMessage("message");
    }
}