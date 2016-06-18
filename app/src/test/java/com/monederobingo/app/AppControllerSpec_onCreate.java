package com.monederobingo.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.ClassMembersAccessor.getFieldValue;
import static com.monederobingo.TestVerifiers.call;
import static org.junit.Assert.assertNotNull;
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
        call(appController).callOnCreateInSuper();
        assertNotNull(getFieldValue(appController, "instance"));
    }
}