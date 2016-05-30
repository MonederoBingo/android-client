package com.monederobingo.app;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class AppControllerTest_onCreate {

    private boolean callOnCreateInSuperCalled = false;
    private boolean getDefaultSharedPreferencesCalled = false;
    private SharedPreferences sharedPreferences;

    @Test
    public void shouldCallOnCreateInSuper() throws Exception {
        //given
        AppController appController = getAppController();
        //when
        appController.onCreate();
        //then
        assertTrue(callOnCreateInSuperCalled);
    }

    @Test
    public void shouldCallGetDefaultSharedPreferences() throws Exception {
        //given
        AppController appController = getAppController();
        //when
        appController.onCreate();
        //then
        assertTrue(getDefaultSharedPreferencesCalled);
    }

    @Test
    public void shouldCreateInstance() throws Exception {
        //given
        AppController appController = getAppController();
        //when
        appController.onCreate();
        AppController instance = AppController.getInstance();
        //then
        assertEquals(appController, instance);
    }

    @NonNull
    private AppController getAppController() {
        return new AppController() {
            @Override
            void callOnCreateInSuper() {
                callOnCreateInSuperCalled = true;
            }

            @Override
            SharedPreferences getDefaultSharedPreferences() {
                getDefaultSharedPreferencesCalled = true;
                return sharedPreferences;
            }
        };
    }
}