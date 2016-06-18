package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_shouldSetSessionCookie extends AppControllerSpec {

    @Test
    public void givenHeadersContainSessionCookie() {
        //given
        doReturn(true).when(headers).containsKey(Constants.Web.SET_COOKIE_KEY);
        doReturn(Constants.Web.JSESSIONID).when(headers).get(Constants.Web.SET_COOKIE_KEY);
        //when
        boolean shouldSetSessionCookie = appController.shouldSetSessionCookie(headers);
        //then
        assertTrue(shouldSetSessionCookie);
    }

    @Test
    public void givenHeadersDoNotContainSessionCookie() {
        //given
        doReturn(false).when(headers).containsKey(Constants.Web.SET_COOKIE_KEY);
        //when
        boolean shouldSetSessionCookie = appController.shouldSetSessionCookie(headers);
        //then
        assertFalse(shouldSetSessionCookie);
    }

    @Test
    public void givenHeadersContainSessionCookieAsNull() {
        //given
        doReturn(true).when(headers).containsKey(Constants.Web.SET_COOKIE_KEY);
        doReturn(null).when(headers).get(Constants.Web.SET_COOKIE_KEY);
        //when
        boolean shouldSetSessionCookie = appController.shouldSetSessionCookie(headers);
        //then
        assertFalse(shouldSetSessionCookie);
    }
}