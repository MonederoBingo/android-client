package com.monederobingo.rest;

import android.support.annotation.NonNull;

import com.android.volley.VolleyError;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomResponseErrorListenerTest_onErrorResponseTest {

    @Mock
    ApiListener apiListener;
    @Mock
    private VolleyError volleyError;

    @Test
    public void shouldThrowExceptionIfNullParameter() {
        try {
            //given
            CustomResponseErrorListener customResponseErrorListener = createCustomResponseErrorListener();
            //when
            customResponseErrorListener.onErrorResponse(null);
        } catch (RuntimeException e) {
            //then
            assertEquals("volleyError should not be null", e.getMessage());
        }
    }

    @Test
    public void shouldCallApiListener() {
        //given
        CustomResponseErrorListener customResponseErrorListener = createCustomResponseErrorListener();
        //when
        customResponseErrorListener.onErrorResponse(volleyError);
        //then
        verify(apiListener).onError(volleyError);
        verify(apiListener).stopLoading();
    }

    @NonNull
    private CustomResponseErrorListener createCustomResponseErrorListener() {
        return new CustomResponseErrorListener(apiListener) {
            @Override
            void logMessage(VolleyError volleyError) {
            }
        };
    }


}