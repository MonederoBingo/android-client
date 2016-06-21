package com.monederobingo.app;

import android.content.SharedPreferences;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.monederobingo.util.MapUtilsWrapper;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
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
    @Mock
    protected MapUtilsWrapper mapUtilsWrapper;

    @Before
    public void baseSetUp() throws Exception {
        appController = spy(new AppController());
        doReturn(requestQueue).when(appController).getRequestQueue();
        doReturn(retryPolicy).when(appController).getRetryPolicy();
    }
}