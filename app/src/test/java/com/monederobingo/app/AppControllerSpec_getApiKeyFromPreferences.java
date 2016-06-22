package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_getApiKeyFromPreferences extends AppControllerSpec {

    @Test
    public void spec() {
        //given
        doReturn("apiKey").when(appController).getFromPreferences(Constants.Preferences.API_KEY);
        //when
        String apiKeyFromPreferences = appController.getApiKeyFromPreferences();
        //then
        call(appController).getFromPreferences(Constants.Preferences.API_KEY);
        assertEquals("apiKey", apiKeyFromPreferences);
    }
}