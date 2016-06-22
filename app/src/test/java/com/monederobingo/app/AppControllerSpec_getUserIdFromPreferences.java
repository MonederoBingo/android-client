package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_getUserIdFromPreferences extends AppControllerSpec {

    @Test
    public void spec() {
        //given
        doReturn("userId").when(appController).getFromPreferences(Constants.Preferences.USER_ID);
        //when
        String userIdFromPreferences = appController.getUserIdFromPreferences();
        //then
        call(appController).getFromPreferences(Constants.Preferences.USER_ID);
        assertEquals("userId", userIdFromPreferences);
    }
}