package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_removePhoneFromPreferences extends AppControllerSpec {

    @Before
    public void setUp() {
        doNothing().when(appController).removeFromPreferences(Constants.Preferences.PHONE_NUMBER);
    }

    @Test
    public void spec() {
        //when
        appController.removePhoneFromPreferences();
        //then
        call(appController).removeFromPreferences(Constants.Preferences.PHONE_NUMBER);
    }
}