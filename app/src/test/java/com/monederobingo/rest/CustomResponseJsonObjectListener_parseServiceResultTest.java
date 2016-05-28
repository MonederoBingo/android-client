package com.monederobingo.rest;

import android.support.annotation.NonNull;

import com.monederobingo.BaseUnitTest;
import com.monederobingo.model.ServiceResult;

import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mock;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


public class CustomResponseJsonObjectListener_parseServiceResultTest extends BaseUnitTest {

    @Mock
    ApiListener apiListener;

    @Mock
    JSONObject jsonObject;

    @Test
    public void parseServiceResult_shouldReturnNonNullWhenEmptyData() throws UnsupportedEncodingException {
        //given
        CustomResponseJsonObjectListener jsonObjectListener = createCustomResponseJsonObjectListener(apiListener);
        //when
        ServiceResult serviceResult = jsonObjectListener.parseServiceResult(jsonObject);
        //then
        assertNotNull(serviceResult);
    }

    @Test
    public void parseServiceResult_shouldParseIsSuccessWhenTrue() throws UnsupportedEncodingException {
        //given
        CustomResponseJsonObjectListener jsonObjectListener = createCustomResponseJsonObjectListener(apiListener);
        when(jsonObject.optBoolean(anyString())).thenReturn(true);
        //when
        ServiceResult serviceResult = jsonObjectListener.parseServiceResult(jsonObject);
        //then
        assertTrue(serviceResult.isSuccess());
    }

    @Test
    public void parseServiceResult_ShouldParseIsSuccessWhenFalse() throws UnsupportedEncodingException {
        //given
        CustomResponseJsonObjectListener jsonObjectListener = createCustomResponseJsonObjectListener(apiListener);
        when(jsonObject.optBoolean(anyString())).thenReturn(false);
        //when
        ServiceResult serviceResult = jsonObjectListener.parseServiceResult(jsonObject);
        //then
        assertFalse(serviceResult.isSuccess());
    }

    @Test
    public void parseServiceResult_shouldParseMessage() throws UnsupportedEncodingException {
        //given
        CustomResponseJsonObjectListener jsonObjectListener = createCustomResponseJsonObjectListener(apiListener);
        when(jsonObject.optString(anyString())).thenReturn("parsedMessage");
        //when
        ServiceResult serviceResult = jsonObjectListener.parseServiceResult(jsonObject);
        //then
        assertEquals("parsedMessage", serviceResult.getMessage());
    }

    @Test
    public void parseServiceResult_shouldParseObject() throws UnsupportedEncodingException {
        //given
        CustomResponseJsonObjectListener jsonObjectListener = createCustomResponseJsonObjectListener(apiListener);
        when(jsonObject.optString(anyString())).thenReturn("parsedObject");
        //when
        ServiceResult serviceResult = jsonObjectListener.parseServiceResult(jsonObject);
        //then
        assertEquals("parsedObject", serviceResult.getMessage());
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