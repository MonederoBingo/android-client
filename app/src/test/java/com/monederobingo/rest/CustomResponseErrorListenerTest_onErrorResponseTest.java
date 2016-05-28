package com.monederobingo.rest;

import android.support.annotation.NonNull;

import com.android.volley.VolleyError;
import com.monederobingo.BaseUnitTest;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class CustomResponseErrorListenerTest_onErrorResponseTest extends BaseUnitTest {

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