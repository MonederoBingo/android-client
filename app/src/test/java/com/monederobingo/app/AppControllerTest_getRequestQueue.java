package com.monederobingo.app;

import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AppControllerTest_getRequestQueue {

    private boolean createVolleyRequestQueueCalled = false;
    @Mock
    private RequestQueue requestQueue;

    @Test
    public void shouldCallCreateVolleyRequestQueue() throws Exception {
        //given
        AppController appController = getAppController();
        //when
        appController.getRequestQueue();
        //then
        assertTrue(createVolleyRequestQueueCalled);
    }

    @Test
    public void shouldNotReturnNull() throws Exception {
        //given
        AppController appController = getAppController();
        //when
        RequestQueue requestQueue = appController.getRequestQueue();
        //then
        assertNull(requestQueue);
    }

    @NonNull
    private AppController getAppController() {
        return new AppController() {
            @NonNull
            @Override
            RequestQueue createVolleyRequestQueue() {
                createVolleyRequestQueueCalled = true;
                return requestQueue;
            }
        };
    }
}