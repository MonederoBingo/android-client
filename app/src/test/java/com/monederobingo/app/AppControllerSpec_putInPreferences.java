package com.monederobingo.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_putInPreferences extends AppControllerSpec {

    @Test
    public void spec() throws Exception {
        //when
        appController.putInPreferences("key", "value");
        //then
        call(appController).getDefaultSharedPreferences();
        call(preferences).edit();
        call(editor).putString("key", "value");
        call(editor).apply();
    }
}