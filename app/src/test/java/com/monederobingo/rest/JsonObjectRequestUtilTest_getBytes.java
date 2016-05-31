package com.monederobingo.rest;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class JsonObjectRequestUtilTest_getBytes {

    @Mock
    private JsonObjectFactory jsonObjectFactory;
    @Mock
    private JSONObject jsonObject;
    @InjectMocks
    private final JsonObjectRequestUtil jsonObjectRequestUtil = new JsonObjectRequestUtil();

    @Before
    public void setUp(){
        jsonObjectRequestUtil.setJsonObjectFactory(jsonObjectFactory);
    }

    @Test
    public void shouldReturnBytesOfParam() {
        //given
        given(jsonObjectFactory.createJsonObject(Matchers.<Map<String, String>>any())).willReturn(jsonObject);
        String str = "test";
        given(jsonObject.toString()).willReturn(str);

        //when
        byte[] bytes = jsonObjectRequestUtil.getBytes(Collections.<String, String>emptyMap());

        //then
        assertTrue(Arrays.equals(str.getBytes(), bytes));
    }

    @Test
    public void shouldReturnBytesFromEmptyString() {
        //given
        given(jsonObjectFactory.createJsonObject(Matchers.<Map<String, String>>any())).willReturn(jsonObject);

        //when
        byte[] bytes = jsonObjectRequestUtil.getBytes(null);

        //then
        assertEquals(0, bytes.length);
    }
}