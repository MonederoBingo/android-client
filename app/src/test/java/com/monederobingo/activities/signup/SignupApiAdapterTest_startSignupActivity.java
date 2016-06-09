package com.monederobingo.activities.signup;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.monederobingo.model.ServiceResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SignupApiAdapterTest_startSignupActivity {

    @Mock
    private SignupActivity signupActivity;
    @Mock
    private ServiceResult serviceResult;
    @Mock
    private Intent intent;
    private SignupApiAdapter signupApiAdapter;
    private boolean getIntentCalled;

    @Before
    public void setUp() {
        getIntentCalled = false;
        initializeClassToTest();
    }

    private void initializeClassToTest() {
        signupApiAdapter = new SignupApiAdapter(signupActivity) {
            @NonNull
            @Override
            Intent getIntent() {
                getIntentCalled = true;
                return intent;
            }
        };
    }

    @Test
    public void methodSpecification() throws Exception {
        //given
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("phoneNumber", "1234567890");
        //when
        signupApiAdapter.startSignupActivity(requestParams);
        //then
        shouldCallGetIntent();
        shouldPutExtra();
        shouldStartActivity();
    }

    private void shouldCallGetIntent() {
        assertTrue(getIntentCalled);
    }

    private void shouldPutExtra() {
        verify(intent).putExtra(SignupActivity.SIGNUP_PHONE, "1234567890");
    }

    private void shouldStartActivity() {
        verify(signupActivity).startActivity(intent);
    }
}