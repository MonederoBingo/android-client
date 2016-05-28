package com.monederobingo.rest;

import com.android.volley.AuthFailureError;
import com.monederobingo.BaseUnitTest;

import org.junit.Test;
import org.mockito.Mock;

import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by SG0951840 on 4/24/2016.
 */
public class JsonObjectRequestUtilTest_getHeaders extends BaseUnitTest {

    @Mock
    private CustomJsonObjectRequest customObjectRequest;
    @Mock
    private Map<String, String> headers;

    @Test
    public void shouldCallGetCustomHeaders() throws AuthFailureError {
        //given
        given(customObjectRequest.customGetHeaders()).willReturn(headers);

        //when
        JsonObjectRequestUtil.getHeaders(customObjectRequest, "", "");

        //then
        verify(customObjectRequest).customGetHeaders();
    }
}