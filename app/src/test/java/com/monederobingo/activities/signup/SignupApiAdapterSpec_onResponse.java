package com.monederobingo.activities.signup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SignupApiAdapterSpec_onResponse extends SignupApiAdapterSpec {

    private Map<String, String> requestParams = new HashMap<>();

    @Before
    public void setUp() {
        requestParams.put("phoneNumber", "1234567890");
        given(serviceResult.getMessage()).willReturn("message");
    }

    @Test
    public void givenServiceResultSuccessIsTrue() throws Exception {
        //given
        given(serviceResult.isSuccess()).willReturn(true);
        //when
        signupApiAdapter.onResponse(serviceResult, requestParams);
        //then
        shouldPutPhoneInPreferences("1234567890");
        shouldStartSignupActivity(requestParams);
        shouldNotSetTextToTvSignupMessage("message");
    }

    @Test
    public void givenServiceResultSuccessIsFalse() throws Exception {
        //given
        given(serviceResult.isSuccess()).willReturn(false);
        //when
        signupApiAdapter.onResponse(serviceResult, requestParams);
        //then
        shouldNotPutPhoneInPreferences("1234567890");
        shouldNotStartSignupActivity(requestParams);
        shouldSetTextToTvSignupMessage("message");
    }

}