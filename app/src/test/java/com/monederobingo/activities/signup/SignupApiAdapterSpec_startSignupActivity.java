package com.monederobingo.activities.signup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class SignupApiAdapterSpec_startSignupActivity extends SignupApiAdapterSpec {

    @Test
    public void spec() throws Exception {
        //given
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("phoneNumber", "1234567890");
        //when
        signupApiAdapter.startSignupActivity(requestParams);
        //then
        shouldCallCreateIntent();
        shouldPutExtra(SignupActivity.SIGNUP_PHONE, "1234567890");
        shouldStartActivity();
    }
}