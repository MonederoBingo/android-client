package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;
import static com.monederobingo.TestVerifiers.neverCall;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_putSmsKeyInPreferences extends AppControllerSpec {

    @Before
    public void setUp() {
        doNothing().when(appController).putInPreferences(Constants.Preferences.SMS_KEY, "smsKey");
    }

    @Test
    public void spec() {
        //when
        appController.putSmsKeyInPreferences("smsKey");
        //then
        call(appController).putInPreferences(Constants.Preferences.SMS_KEY, "smsKey");
    }
}