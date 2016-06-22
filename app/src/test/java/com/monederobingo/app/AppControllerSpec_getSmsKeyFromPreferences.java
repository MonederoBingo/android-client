package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_getSmsKeyFromPreferences extends AppControllerSpec {

    @Test
    public void spec() {
        //given
        doReturn("smsKey").when(appController).getFromPreferences(Constants.Preferences.SMS_KEY);
        //when
        String smsKeyFromPreferences = appController.getSmsKeyFromPreferences();
        //then
        call(appController).getFromPreferences(Constants.Preferences.SMS_KEY);
        assertEquals("smsKey", smsKeyFromPreferences);
    }
}