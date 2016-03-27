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

public class RestClientImplTest extends BaseUnitTest {

    @Mock
    ApiListener apiListener;
    @Mock
    AppController appController;
    @Mock
    CustomJsonObjectRequest customJsonObjectRequest;

    @Test
    public void getInstance_shouldReturnSameInstance() {
        assertEquals(RestClientImpl.getInstance(), RestClientImpl.getInstance());
    }

    @Test
    public void callApi_shouldAddRequestToQueue() {
        RestClientImpl restClient = createRestClientImpl();
        restClient.callApi(1, "", new HashMap<String, String>(), apiListener, null);
        verify(appController).addToRequestQueue(Matchers.<Request<Object>>anyObject(), anyObject());
    }

    @Test
    public void callAuth_shouldAddRequestToQueue() {
        RestClientImpl restClient = createRestClientImpl();
        restClient.callAuth(1, "", new HashMap<String, String>(), apiListener, null);
        verify(appController).addToRequestQueue(Matchers.<Request<Object>>anyObject(), anyObject());
    }

    @Test
    public void stopService_shouldCancelPendingRequests() {
        RestClientImpl restClient = createRestClientImpl();
        restClient.stopService("");
        verify(appController).cancelPendingRequests(anyString());
    }

    @NonNull
    private RestClientImpl createRestClientImpl() {
        return new RestClientImpl(appController) {
            @NonNull
            @Override
            CustomJsonObjectRequest createRequest(int method, String url, ApiListener apiListener,
                                                  Map<String, String> params, String userId, String apiKey) {
                return customJsonObjectRequest;
            }
        };
    }
}