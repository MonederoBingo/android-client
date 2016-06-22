package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_putPhoneInPreferences extends AppControllerSpec {

    @Before
    public void setUp() {
        doNothing().when(appController).putInPreferences(Constants.Preferences.PHONE_NUMBER, "phone");
    }

    @Test
    public void spec() {
        //when
        appController.putPhoneInPreferences("phone");
        //then
        call(appController).putInPreferences(Constants.Preferences.PHONE_NUMBER, "phone");
    }
}