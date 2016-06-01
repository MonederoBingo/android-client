package com.monederobingo.app;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.monederobingo.common.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest_getPhoneFromPreferences {

    @Mock
    private SharedPreferences preferences;
    @Mock
    private SharedPreferences.Editor editor;

    @Test
    public void shouldGetThePhoneNumber() throws NoSuchFieldException, IllegalAccessException {
        //given
        AppController appController = getAppController();
        String phone = "phone";
        given(preferences.getString(Constants.Preferences.PHONE_NUMBER, "")).willReturn(phone);
        //when
        String phoneNumber = appController.getPhoneFromPreferences();
        //then
        assertEquals(phoneNumber, phone);
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