package com.monederobingo.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginResult_getUserId {

    @Test
    public void shouldBeTheSameAsConstructorParameter() {
        //given
        LoginResult loginResult = new LoginResult("userId", "");
        //when
        String userId = loginResult.getUserId();
        //then
        assertEquals("userId" , userId);
    }
}