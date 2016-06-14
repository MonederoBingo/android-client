package com.monederobingo.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_onCreate extends AppControllerSpec {

    @Test
    public void spec() throws NoSuchFieldException, IllegalAccessException {
        //given
        doNothing().when(appController).callOnCreateInSuper();
        //when
        appController.onCreate();
        //then
        shouldCallOnCreateInSuper();
        instanceShouldNotBeNull();
    }
}