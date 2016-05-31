package com.monederobingo.app;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.monederobingo.common.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest_addSessionCookie {

    @Mock
    private Map<String, String> headers;
    @Mock
    private SharedPreferences preferences;

    @Test
    public void shouldCallGetStringOnPreferences() throws NoSuchFieldException, IllegalAccessException {
        //given
        AppController appController = getAppController();
        given(preferences.getString(Constants.Web.SESSION_COOKIE, "")).willReturn("");
        //when
        appController.addSessionCookie(headers);
        //then
        verify(preferences).getString(Constants.Web.SESSION_COOKIE, "");
    }

    @Test
    public void shouldCallContainsKeyOnHeadersWhenNonEmptySessionId() throws NoSuchFieldException, IllegalAccessException {
        //given
        AppController appController = getAppController();
        given(preferences.getString(Constants.Web.SESSION_COOKIE, "")).willReturn("1234");
        //when
        appController.addSessionCookie(headers);
        //then
        verify(headers).containsKey(Constants.Web.COOKIE_KEY);
    }

    @Test
    public void shouldCallPutOnHeadersWhenNonEmptySessionId() throws NoSuchFieldException, IllegalAccessException {
        //given
        AppController appController = getAppController();
        given(preferences.getString(Constants.Web.SESSION_COOKIE, "")).willReturn("1234");
        given(headers.containsKey(Constants.Web.COOKIE_KEY)).willReturn(false);
        //when
        appController.addSessionCookie(headers);
        //then
        verify(headers).put(Constants.Web.COOKIE_KEY, "JSESSIONID=1234");
    }

    @Test
    public void shouldCallAddCookiesToHeadersWhenNonEmpty() throws NoSuchFieldException, IllegalAccessException {
        //given
        AppController appController = getAppController();
        given(preferences.getString(Constants.Web.SESSION_COOKIE, "")).willReturn("1234");
        given(headers.containsKey(Constants.Web.COOKIE_KEY)).willReturn(true);
        given(headers.get(Constants.Web.COOKIE_KEY)).willReturn("cookie");
        //when
        appController.addSessionCookie(headers);
        //then
        verify(headers).put(Constants.Web.COOKIE_KEY, "JSESSIONID=1234; cookie");
    }

    @Test
    public void shouldNotCallContainsKeyOnHeadersWhenEmptySessionId() throws NoSuchFieldException, IllegalAccessException {
        //given
        AppController appController = getAppController();
        given(preferences.getString(Constants.Web.SESSION_COOKIE, "")).willReturn("");
        //when
        appController.addSessionCookie(headers);
        //then
        verify(headers, never()).containsKey(Constants.Web.COOKIE_KEY);
    }

    @NonNull
    private AppController getAppController() throws NoSuchFieldException, IllegalAccessException {
        AppController appController = new AppController();
        Field requestQueueField = appController.getClass().getDeclaredField("preferences");
        requestQueueField.setAccessible(true);
        requestQueueField.set(appController, preferences);
        return appController;
    }
}