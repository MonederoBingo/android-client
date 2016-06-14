package com.monederobingo.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_cancelPendingRequests extends AppControllerSpec {

    @Test
    public void givenRequestQueueIsNull() throws NoSuchFieldException, IllegalAccessException {
        //given
        Field requestQueueField = getRequestQueueField();
        requestQueueField.set(appController, null);
        //when
        appController.cancelPendingRequests(tag);
        //then
        shouldNotCallCancelAllOnRequestQueue();
    }

    @Test
    public void givenRequestQueueIsNotNull() throws NoSuchFieldException, IllegalAccessException {
        //given
        Field requestQueueField = getRequestQueueField();
        requestQueueField.set(appController, requestQueue);
        //when
        appController.cancelPendingRequests(tag);
        //then
        shouldCallCancelAllOnRequestQueue();
    }
}