package com.monederobingo.rest;

import com.android.volley.Request;
import com.monederobingo.app.AppController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RestClientImplTest_callAuth {

    @Mock
    ApiListener apiListener;
    @Mock
    AppController appController;
    @Mock
    CustomJsonObjectRequest request;

    @Test
    public void callAuth_shouldAddRequestToQueue() {
        //given
        RestClientImpl restClient = RestClientImplTestCommon.createRestClientImpl(appController, request);
        //when
        restClient.callAuth(1, "", new HashMap<String, String>(), apiListener, null);
        //then
        verify(appController).addToRequestQueue(Matchers.<Request<Object>>anyObject(), anyObject());
    }
}