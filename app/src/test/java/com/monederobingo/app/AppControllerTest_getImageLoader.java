package com.monederobingo.app;

import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;

public class AppControllerTest_getImageLoader {

    private boolean getRequestQueueCalled = false;
    private boolean createImageLoaderCalled = false;
    @Mock
    private RequestQueue requestQueue;
    @Mock
    private ImageLoader imageLoader;

    @Test
    public void shouldCallGetRequestQueue() {
        //given
        AppController appController = getAppController();
        //when
        appController.getImageLoader();
        //then
        assertTrue(getRequestQueueCalled);
    }

    @Test
    public void shouldCallCreateImageLoader() {
        //given
        AppController appController = getAppController();
        //when
        appController.getImageLoader();
        //then
        assertTrue(createImageLoaderCalled);
    }

    @NonNull
    private AppController getAppController() {
        return new AppController() {
            @Override
            public RequestQueue getRequestQueue() {
                getRequestQueueCalled = true;
                return requestQueue;
            }

            @NonNull
            @Override
            ImageLoader createImageLoader() {
                createImageLoaderCalled = true;
                return imageLoader;
            }
        };
    }
}