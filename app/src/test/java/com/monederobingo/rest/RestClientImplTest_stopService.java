package com.monederobingo.rest;

import com.monederobingo.app.AppController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RestClientImplTest_stopService {

    @Mock
    ApiListener apiListener;
    @Mock
    AppController appController;
    @Mock
    CustomJsonObjectRequest request;

    @Test
    public void stopService_shouldCancelPendingRequests() {
        RestClientImpl restClient = RestClientImplTestCommon.createRestClientImpl(appController, request);
        restClient.stopService("");
        verify(appController).cancelPendingRequests(anyString());
    }
}