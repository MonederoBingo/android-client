package com.monederobingo.common.parsers;

import com.monederobingo.factories.JSONObjectFactory;
import com.monederobingo.model.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class LoginResultParserTest_getLoginResult {

    @Mock
    private JSONObjectFactory jsonObjectFactory;
    @Mock
    private JSONObject jsonObject;

    @Test
    public void shouldParseClientUserId() throws JSONException {
        //given
        LoginResultParser loginResultParser = new LoginResultParser(jsonObjectFactory);
        given(jsonObjectFactory.createJSONObject(anyString())).willReturn(jsonObject);
        given(jsonObject.optString("clientUserId")).willReturn("1");
        //when
        LoginResult loginResult = loginResultParser.getLoginResult("");
        //then
        assertEquals("1", loginResult.getUserId());
    }

    @Test
    public void shouldParseApiKey() throws JSONException {
        //given
        LoginResultParser loginResultParser = new LoginResultParser(jsonObjectFactory);
        given(jsonObjectFactory.createJSONObject(anyString())).willReturn(jsonObject);
        given(jsonObject.optString("apiKey")).willReturn("key");
        //when
        LoginResult loginResult = loginResultParser.getLoginResult("");
        //then
        assertEquals("key", loginResult.getApiKey());
    }
}