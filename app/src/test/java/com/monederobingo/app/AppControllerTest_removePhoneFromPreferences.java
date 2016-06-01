package com.monederobingo.app;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.monederobingo.common.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest_removePhoneFromPreferences {

    @Mock
    private SharedPreferences preferences;
    @Mock
    private SharedPreferences.Editor editor;

    @Test
    public void shouldCallEditOnPreferences() throws NoSuchFieldException, IllegalAccessException {
        //given
        AppController appController = getAppController();
        given(preferences.edit()).willReturn(editor);
        //when
        appController.removePhoneFromPreferences();
        //then
        verify(preferences).edit();
    }

    @Test
    public void shouldCallRemoveOnEditor() throws NoSuchFieldException, IllegalAccessException {
        //given
        AppController appController = getAppController();
        given(preferences.edit()).willReturn(editor);
        //when
        appController.removePhoneFromPreferences();
        //then
        verify(editor).remove(Constants.Preferences.PHONE_NUMBER);
    }

    @Test
    public void shouldCallApplyOnEditor() throws NoSuchFieldException, IllegalAccessException {
        //given
        AppController appController = getAppController();
        given(preferences.edit()).willReturn(editor);
        //when
        appController.removePhoneFromPreferences();
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