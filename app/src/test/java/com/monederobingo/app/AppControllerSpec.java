package com.monederobingo.app;

import android.content.SharedPreferences;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import org.junit.Before;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class AppControllerSpec {

    protected AppController appController;
    @Mock
    protected Request<?> request;
    @Mock
    protected Object tag;
    @Mock
    protected RequestQueue requestQueue;
    @Mock
    protected DefaultRetryPolicy retryPolicy;
    @Mock
    protected Map<String, String> headers;
    @Mock
    protected SharedPreferences preferences;
    @Mock
    protected ImageLoader imageLoader;

    @Before
    public void baseSetUp() throws Exception {
        appController = spy(new AppController());
        doReturn(requestQueue).when(appController).getRequestQueue();
        doReturn(retryPolicy).when(appController).getRetryPolicy();
    }

    protected void instanceShouldNotBeNull() throws NoSuchFieldException, IllegalAccessException {
        Field field = AppController.class.getDeclaredField("instance");
        field.setAccessible(true);
        assertNotNull(field.get(appController));
    }

    protected Field getImageLoaderField() throws NoSuchFieldException, IllegalAccessException {
        Field field = AppController.class.getDeclaredField("imageLoader");
        field.setAccessible(true);
        return field;
    }

    protected Field getRequestQueueField() throws NoSuchFieldException, IllegalAccessException {
        Field field = AppController.class.getDeclaredField("requestQueue");
        field.setAccessible(true);
        return field;
    }
}