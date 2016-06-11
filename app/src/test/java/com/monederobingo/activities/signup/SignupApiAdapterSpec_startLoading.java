package com.monederobingo.activities.signup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SignupApiAdapterSpec_startLoading extends SignupApiAdapterSpec {

    @Test
    public void spec() throws Exception {
        //when
        signupApiAdapter.startLoading();
        //then
        shouldDisableSignUpButton();
        shouldSetProgressBarVisible();
    }

}