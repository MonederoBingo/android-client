package com.monederobingo.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_getNonNull extends AppControllerSpec {

    @Test
    public void givenParameterIsNull() throws NoSuchFieldException, IllegalAccessException {
        //when
        Object nonNullTag = appController.getNonNullTag(null);
        //then
        assertEquals(AppController.TAG, nonNullTag);
    }


    @Test
    public void givenParameterIsNonNull() throws NoSuchFieldException, IllegalAccessException {
        //given
        Object tagObject = new Object();
        //when
        Object nonNullTag = appController.getNonNullTag(tagObject);
        //then
        assertEquals(tagObject, nonNullTag);
    }
}