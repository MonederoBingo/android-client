package com.monederobingo.activities.signup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SignupApiAdapterSpec_startSignupActivity extends SignupApiAdapterSpec {

    @Test
    public void methodSpecification() throws Exception {
        //given
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("phoneNumber", "1234567890");
        //when
        signupApiAdapter.startSignupActivity(requestParams);
        //then
        shouldCallCreateIntent();
        shouldPutExtra("1234567890");
        shouldStartActivity();
    }
}