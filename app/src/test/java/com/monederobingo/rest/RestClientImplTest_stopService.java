package com.monederobingo.rest;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.monederobingo.BaseUnitTest;
import com.monederobingo.app.AppController;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class RestClientImplTest_stopService extends BaseUnitTest {

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