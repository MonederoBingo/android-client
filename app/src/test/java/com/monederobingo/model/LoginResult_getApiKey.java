package com.monederobingo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginResult_getApiKey {

    @Test
    public void shouldBeTheSameAsConstructorParameter() {
        //given
        LoginResult loginResult = new LoginResult("", "apiKey");
        //when
        String apiKey = loginResult.getApiKey();
        //then
        assertEquals("apiKey" , apiKey);
    }
}