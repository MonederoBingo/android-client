package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_putUserIdInPreferences extends AppControllerSpec {

    @Before
    public void setUp() {
        doNothing().when(appController).putInPreferences(Constants.Preferences.USER_ID, "userId");
    }

    @Test
    public void spec() {
        //when
        appController.putUserIdInPreferences("userId");
        //then
        call(appController).putInPreferences(Constants.Preferences.USER_ID, "userId");
    }
}