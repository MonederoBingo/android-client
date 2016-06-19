package com.monederobingo.activities.signup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SignupApiAdapterSpec_stopLoading extends SignupApiAdapterSpec {

    @Test
    public void spec() throws Exception {
        //when
        signupApiAdapter.stopLoading();
        //then
        shouldEnableSignUpButton();
        shouldSetProgressBarInvisible();
    }
}