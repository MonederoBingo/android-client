package com.monederobingo.rest;

import com.monederobingo.BaseUnitTest;
import com.monederobingo.app.AppController;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class RestClientImplTest_getInstance extends BaseUnitTest {

    @Mock
    ApiListener apiListener;
    @Mock
    AppController appController;
    @Mock
    CustomJsonObjectRequest customJsonObjectRequest;

    @Test
    public void shouldReturnSameInstance() {
        assertEquals(RestClientImpl.getInstance(), RestClientImpl.getInstance());
    }
}