package com.monederobingo.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest_getNonNullTag extends AppControllerSpec {

    @Test
    public void shouldReturnParameterWhenNonNull() throws Exception {
        //when
        Object nonNullTag = appController.getNonNullTag(tag);
        //then
        assertEquals(tag, nonNullTag);
    }
}