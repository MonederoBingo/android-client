package com.monederobingo.rest;

import android.support.annotation.NonNull;

import com.monederobingo.BaseUnitTest;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;


public class CustomResponseJsonObjectListenerTest extends BaseUnitTest {

    @Mock
    ApiListener apiListener;

    @Mock
    JSONObject jsonObject;

    @Test
    public void onResponse_shouldWorkFine() throws JSONException {
        CustomResponseJsonObjectListener jsonObjectListener = createCustomResponseJsonObjectListener();
        jsonObjectListener.onResponse(jsonObject);
        verify(apiListener).stopLoading();
    }

    @NonNull
    private CustomResponseJsonObjectListener createCustomResponseJsonObjectListener() {
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