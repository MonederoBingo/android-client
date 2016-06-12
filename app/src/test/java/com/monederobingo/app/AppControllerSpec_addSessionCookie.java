package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

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
        shouldCallGetSessionIdCookieString();
        shouldCallGetCookieString();
        shouldPutCookieOnHeaders(Constants.Web.COOKIE_KEY, "JSESSIONID=1234;cookie");
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
        shouldNotCallGetSessionIdCookieString();
        shouldNotCallGetCookieString();
        shouldNotPutCookieOnHeaders(Constants.Web.COOKIE_KEY, "JSESSIONID=1234;cookie");
    }
}