package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;
import static com.monederobingo.TestVerifiers.neverCall;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_checkSessionCookie extends AppControllerSpec {

    @Before
    public void setUp() {
        doReturn("cookie").when(appController).getCookieFromHeaders(headers);
        doNothing().when(appController).putInPreferences(Constants.Web.JSESSIONID, "cookie");
    }

    @Test
    public void givenShouldSetSessionCookieIsTrue() {
        //given
        doReturn(true).when(appController).shouldSetSessionCookie(headers);
        //when
        appController.checkSessionCookie(headers);
        //then
        call(appController).getCookieFromHeaders(headers);
        call(appController).putInPreferences(Constants.Web.JSESSIONID, "cookie");
    }

    @Test
    public void givenShouldSetSessionCookieIsFalse() {
        //given
        doReturn(false).when(appController).shouldSetSessionCookie(headers);
        //when
        appController.checkSessionCookie(headers);
        //then
        neverCall(appController).getCookieFromHeaders(headers);
        neverCall(appController).putInPreferences(Constants.Web.JSESSIONID, "cookie");
    }
}