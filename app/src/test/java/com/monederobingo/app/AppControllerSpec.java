package com.monederobingo.app;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.junit.Before;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
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

    @Before
    public void baseSetUp() throws Exception {
        appController = spy(new AppController());
        doReturn(requestQueue).when(appController).getRequestQueue();
        doReturn(retryPolicy).when(appController).getRetryPolicy();
    }

    protected void shouldCallGetNonNullTag() {
        verify(appController).getNonNullTag(tag);
    }

    protected void shouldCallSetTagOnRequestParameter() {
        verify(request).setTag(tag);
    }

    protected void shouldCallGetRetryPolicy() throws Exception {
        verify(appController).getRetryPolicy();
    }

    protected void shouldCallSetRetryPolicyOnRequestParameter() throws Exception {
        verify(request).setRetryPolicy(retryPolicy);
    }

    protected void shouldCallGetRequestQueue() {
        verify(appController).getRequestQueue();
    }

    protected void shouldCallAddOnRequestQueue() {
        verify(requestQueue).add(request);
    }
}