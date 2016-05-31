package com.monederobingo.rest;

import com.monederobingo.app.AppController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RestClientImplTest_getInstance {

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