package com.monederobingo.rest;

import com.monederobingo.BaseUnitTest;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

public class JsonObjectRequestUtilTest_getBytes extends BaseUnitTest {

    @InjectMocks
    private final JsonObjectRequestUtil jsonObjectRequestUtil = new JsonObjectRequestUtil();
    @Mock
    private JsonObjectFactory jsonObjectFactory;
    @Mock
    private JSONObject jsonObject;

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
        byte[] bytes = JsonObjectRequestUtil.getBytes(Collections.<String, String>emptyMap());

        //then
        assertTrue(Arrays.equals(str.getBytes(), bytes));
    }

    @Test
    public void shouldReturnBytesFromEmptyString() {
        //given
        given(jsonObjectFactory.createJsonObject(Matchers.<Map<String, String>>any())).willReturn(jsonObject);

        //when
        byte[] bytes = JsonObjectRequestUtil.getBytes(null);

        //then
        assertEquals(0, bytes.length);
    }
}