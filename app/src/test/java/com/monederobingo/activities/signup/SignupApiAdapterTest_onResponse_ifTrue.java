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
        initializeClassToTest();
        signupApiAdapter.setAppController(appController);
    }

    private void initializeClassToTest() {
        signupApiAdapter = new SignupApiAdapter(signupActivity) {
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
    public void methodSpecification() throws Exception {
        //given
        requestParams.put("phoneNumber", "1234567890");
        given(serviceResult.getMessage()).willReturn("message");
        //when
        signupApiAdapter.onResponse(serviceResult, requestParams);
        //then
        shouldPutPhoneInPreferences();
        shouldCallStartSignupActivity();
        shouldNotSetTextToTvSignupMessage();
    }

    private void shouldPutPhoneInPreferences() {
        verify(appController).putPhoneInPreferences("1234567890");
    }

    private void shouldCallStartSignupActivity() {
        assertTrue(startSignupAcitivityCalled);
    }

    public void shouldNotSetTextToTvSignupMessage() {
        verify(signupActivity, never()).setTextToTvSignupMessage("message");
    }
}