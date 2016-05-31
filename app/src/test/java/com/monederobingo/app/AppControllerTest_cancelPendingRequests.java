package com.monederobingo.app;

import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest_cancelPendingRequests {

    @Mock
    private Object tag;
    @Mock
    private RequestQueue requestQueue;

    @Test
    public void shouldNotCallCancelAllWhenRequestQueueIsNull() throws Exception {
        //given
        AppController appController = getAppController(null);
        //when
        appController.cancelPendingRequests(tag);
        //then
        verify(requestQueue, never()).cancelAll(tag);
    }

    @Test
    public void shouldCallCancelAllWhenRequestQueueIsNotNull() throws Exception {
        //given
        AppController appController = getAppController(requestQueue);
        //when
        appController.cancelPendingRequests(tag);
        //then
        verify(this.requestQueue).cancelAll(tag);
    }

    @NonNull
    private AppController getAppController(RequestQueue requestQueue) throws NoSuchFieldException, IllegalAccessException {
        AppController appController = new AppController();
        Field requestQueueField = appController.getClass().getDeclaredField("requestQueue");
        requestQueueField.setAccessible(true);
        requestQueueField.set(appController, requestQueue);
        return appController;
    }
}