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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest_checkSessionCookie {

    @Mock
    private Map<String, String> headers;
    @Mock
    private SharedPreferences preferences;
    @Mock
    private SharedPreferences.Editor editor;

    @Test
    public void shouldCallContainsKeyOnHeadersParameter() {
        //given
        AppController appController = new AppController();
        //when
        appController.checkSessionCookie(headers);
        //then
        verify(headers).containsKey(Constants.Web.SET_COOKIE_KEY);
    }

    @Test
    public void shouldCallGetOnHeadersParameter() {
        //given
        AppController appController = new AppController();
        given(headers.containsKey(Constants.Web.SET_COOKIE_KEY)).willReturn(true);
        given(headers.get(Constants.Web.SET_COOKIE_KEY)).willReturn("");
        //when
        appController.checkSessionCookie(headers);
        //then
        verify(headers).get(Constants.Web.SET_COOKIE_KEY);
    }

    @Test
    public void shouldCallEditOnPreferencesWhenThereIsACookieHeader() throws NoSuchFieldException, IllegalAccessException {
        //given
        given(headers.containsKey(Constants.Web.SET_COOKIE_KEY)).willReturn(true);
        given(headers.get(Constants.Web.SET_COOKIE_KEY)).willReturn("JSESSIONID=1234;other");
        given(preferences.edit()).willReturn(editor);
        AppController appController = getAppController();
        //when
        appController.checkSessionCookie(headers);
        //then
        verify(preferences).edit();
    }

    @Test
    public void shouldCallPutStringOnEditor() throws NoSuchFieldException, IllegalAccessException {
        //given
        given(headers.containsKey(Constants.Web.SET_COOKIE_KEY)).willReturn(true);
        given(headers.get(Constants.Web.SET_COOKIE_KEY)).willReturn("JSESSIONID=1234;other");
        given(preferences.edit()).willReturn(editor);
        AppController appController = getAppController();
        //when
        appController.checkSessionCookie(headers);
        //then
        verify(editor).putString(Constants.Web.SESSION_COOKIE, "1234");
    }

    @Test
    public void shouldCallApplyOnEditor() throws NoSuchFieldException, IllegalAccessException {
        //given
        given(headers.containsKey(Constants.Web.SET_COOKIE_KEY)).willReturn(true);
        given(headers.get(Constants.Web.SET_COOKIE_KEY)).willReturn("JSESSIONID=1234;other");
        given(preferences.edit()).willReturn(editor);
        AppController appController = getAppController();
        //when
        appController.checkSessionCookie(headers);
        //then
        verify(editor).apply();
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