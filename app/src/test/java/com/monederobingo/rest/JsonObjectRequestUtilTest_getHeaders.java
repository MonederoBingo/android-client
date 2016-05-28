package com.monederobingo.rest;

import com.android.volley.AuthFailureError;
import com.monederobingo.BaseUnitTest;
import com.monederobingo.app.AppController;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class JsonObjectRequestUtilTest_getHeaders extends BaseUnitTest {

    @Mock
    private CustomJsonObjectRequest customObjectRequest;
    @Mock
    private Map<String, String> headers;
    @Mock
    private AppController appController;
    @InjectMocks
    private JsonObjectRequestUtil jsonObjectRequestUtil;

    @Before
    public void setUp() {
        jsonObjectRequestUtil = new JsonObjectRequestUtil();
        jsonObjectRequestUtil.setAppController(appController);
    }

    @Test
    public void shouldCallGetCustomHeaders() throws AuthFailureError {
        //given
        given(customObjectRequest.customGetHeaders()).willReturn(headers);

        //when
        jsonObjectRequestUtil.getHeaders(customObjectRequest, "", "");

        //then
        verify(customObjectRequest).customGetHeaders();
    }
}