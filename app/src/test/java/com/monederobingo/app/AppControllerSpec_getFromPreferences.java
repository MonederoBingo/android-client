package com.monederobingo.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_getFromPreferences extends AppControllerSpec {

    @Test
    public void spec() throws Exception {
        //when
        appController.getFromPreferences("key");
        //then
        call(appController).getDefaultSharedPreferences();
        call(preferences).getString("key", "");
    }
}