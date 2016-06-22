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
public class AppControllerSpec_putApiKeyInPreferences extends AppControllerSpec {

    @Before
    public void setUp() {
        doNothing().when(appController).putInPreferences(Constants.Preferences.API_KEY, "apiKey");
    }

    @Test
    public void spec() {
        //when
        appController.putApiKeyInPreferences("apiKey");
        //then
        call(appController).putInPreferences(Constants.Preferences.API_KEY, "apiKey");
    }
}