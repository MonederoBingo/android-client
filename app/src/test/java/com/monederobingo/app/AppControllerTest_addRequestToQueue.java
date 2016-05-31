package com.monederobingo.app;

import android.support.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest_addRequestToQueue {

    @Mock
    private Request<? extends Object> request;
    @Mock
    private Object tag;
    @Mock
    private RequestQueue requestQueue;
    @Mock
    private DefaultRetryPolicy retryPolicy;

    @Test
    public void shouldCallSetTagOnRequestParameterUsingTagParameter() throws Exception {
        //given
        AppController appController = getAppController();
        //when
        appController.addToRequestQueue(request, tag);
        //then
        verify(request).setTag(tag);
    }

    @Test
    public void shouldCallSetTagOnRequestParameterUsingAppControllerTag() throws Exception {
        //given
        AppController appController = getAppController();
        //when
        appController.addToRequestQueue(request, null);
        //then
        verify(request).setTag(AppController.class.getSimpleName());
    }

    @Test public void shouldCallSetRetryPolicyOnRequestParameter() throws Exception {
        //given
        AppController appController = getAppController();
        //when
        appController.addToRequestQueue(request, tag);
        //then
        verify(request).setRetryPolicy(retryPolicy);
    }

    @Test public void shouldCallAddOnRequestQueue() throws Exception {
        //given
        AppController appController = getAppController();
        //when
        appController.addToRequestQueue(request, tag);
        //then
        verify(requestQueue).add(request);
    }

    @NonNull
    private AppController getAppController() {
        return new AppController() {
            @Override
            public RequestQueue getRequestQueue() {
                return requestQueue;
            }

            @NonNull
            @Override
            DefaultRetryPolicy getRetryPolicy() {
                return retryPolicy;
            }
        };
    }
}