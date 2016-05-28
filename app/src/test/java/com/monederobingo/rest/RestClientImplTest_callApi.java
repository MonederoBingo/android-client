package com.monederobingo.rest;

import com.android.volley.Request;
import com.monederobingo.BaseUnitTest;
import com.monederobingo.app.AppController;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.HashMap;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;

public class RestClientImplTest_callApi extends BaseUnitTest {

    @Mock
    ApiListener apiListener;
    @Mock
    AppController appController;
    @Mock
    CustomJsonObjectRequest request;

    @Test
    public void shouldAddRequestToQueue() {
        //given
        RestClientImpl restClient = RestClientImplTestCommon.createRestClientImpl(appController, request);
        //when
        restClient.callApi(1, "", new HashMap<String, String>(), apiListener, null);
        //then
        verify(appController).addToRequestQueue(Matchers.<Request<Object>>anyObject(), anyObject());
    }
}