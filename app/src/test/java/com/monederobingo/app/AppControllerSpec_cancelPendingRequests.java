package com.monederobingo.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.ClassMembersAccessor.setFieldValue;
import static com.monederobingo.TestVerifiers.call;
import static com.monederobingo.TestVerifiers.neverCall;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_cancelPendingRequests extends AppControllerSpec {

    @Test
    public void givenRequestQueueIsNull() throws NoSuchFieldException, IllegalAccessException {
        //given
        setFieldValue(appController, "requestQueue", null);
        //when
        appController.cancelPendingRequests(tag);
        //then
        neverCall(requestQueue).cancelAll(tag);
    }

    @Test
    public void givenRequestQueueIsNotNull() throws NoSuchFieldException, IllegalAccessException {
        //given
        setFieldValue(appController, "requestQueue", requestQueue);
        //when
        appController.cancelPendingRequests(tag);
        //then
        call(requestQueue).cancelAll(tag);
    }
}