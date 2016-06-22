package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_getPhoneFromPreferences extends AppControllerSpec {

    @Test
    public void spec() {
        //given
        doReturn("1234").when(appController).getFromPreferences(Constants.Preferences.PHONE_NUMBER);
        //when
        String phoneFromPreferences = appController.getPhoneFromPreferences();
        //then
        call(appController).getFromPreferences(Constants.Preferences.PHONE_NUMBER);
        assertEquals("1234", phoneFromPreferences);
    }
}