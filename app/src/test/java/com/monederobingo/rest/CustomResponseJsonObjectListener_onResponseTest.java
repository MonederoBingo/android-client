package com.monederobingo.rest;

import android.support.annotation.NonNull;

import com.monederobingo.model.ServiceResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomResponseJsonObjectListener_onResponseTest {

    @Mock
    ApiListener apiListener;

    @Mock
    JSONObject jsonObject;

    @Test
    public void shouldCallApiListener() throws JSONException {
        //given
        CustomResponseJsonObjectListener jsonObjectListener = createCustomResponseJsonObjectListener(apiListener);
        when(jsonObject.optBoolean(anyString())).thenReturn(true);
        when(jsonObject.optString(anyString())).thenReturn("");
        //when
        jsonObjectListener.onResponse(jsonObject);
        //then
        verify(apiListener).stopLoading();
        verify(apiListener).onResponse((ServiceResult) anyObject(), Matchers.<Map<String, String>>anyObject());
    }

    @NonNull
    private CustomResponseJsonObjectListener createCustomResponseJsonObjectListener(final ApiListener apiListener) {
        return new CustomResponseJsonObjectListener(apiListener, new HashMap<String, String>()) {
            @Override
            void logResponse(JSONObject jsonObject) {
            }

            @Override
            void logException(Exception e) {
            }
        };
    }
}