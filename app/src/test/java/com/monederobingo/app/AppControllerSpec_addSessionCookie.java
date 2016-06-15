package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;
import static com.monederobingo.TestVerifiers.neverCall;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_addSessionCookie extends AppControllerSpec {

    @Test
    public void givenJSessionIdIsInPreferences() {
        //given
        doReturn(true).when(appController).isJSessionIdInPreferences();
        doReturn("JSESSIONID=1234").when(appController).getSessionIdCookieString();
        doReturn(";cookie").when(appController).getCookieString(headers);
        //when
        appController.addSessionCookie(headers);
        //then
        call(appController).getSessionIdCookieString();
        call(appController).getCookieString(headers);
        call(headers).put(Constants.Web.COOKIE_KEY, "JSESSIONID=1234;cookie");
    }

    @Test
    public void givenJSessionIdIsNotInPreferences() {
        //given
        doReturn(false).when(appController).isJSessionIdInPreferences();
        doReturn("JSESSIONID=1234").when(appController).getSessionIdCookieString();
        doReturn(";cookie").when(appController).getCookieString(headers);
        //when
        appController.addSessionCookie(headers);
        //then
        neverCall(appController).getSessionIdCookieString();
        neverCall(appController).getCookieString(headers);
        neverCall(headers).put(Constants.Web.COOKIE_KEY, "JSESSIONID=1234;cookie");
    }
}