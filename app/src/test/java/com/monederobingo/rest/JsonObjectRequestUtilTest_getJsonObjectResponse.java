package com.monederobingo.rest;

import com.android.volley.NetworkResponse;
import com.monederobingo.BaseUnitTest;
import com.monederobingo.app.AppController;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.Map;

import static org.mockito.Mockito.verify;

public class JsonObjectRequestUtilTest_getJsonObjectResponse extends BaseUnitTest {

    @Mock
    private NetworkResponse networkResponse;
    @Mock
    private CustomJsonObjectRequest jsonObjectRequest;
    @Mock
    private AppController appController;

    @Before
    public void setUp() {
        JsonObjectRequestUtil.setAppController(appController);
    }

    @Test
    public void shouldCheckSessionCookie() {
        //when
        JsonObjectRequestUtil.getJsonObjectResponse(networkResponse, jsonObjectRequest);

        //then
        verify(appController).checkSessionCookie(Matchers.<Map<String, String>>any());
    }

    @Test
    public void shouldParseNetworkResponse() {
        //when
        JsonObjectRequestUtil.getJsonObjectResponse(networkResponse, jsonObjectRequest);

        //then
        verify(jsonObjectRequest).customParseNetworkResponse(Matchers.<NetworkResponse>any());
    }
}