package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerTest_isJSessionIdInPreferences extends AppControllerSpec {

    @Test
    public void givenJSessionIdIsNotEmpty() {
        //given
        doReturn(preferences).when(appController).getDefaultSharedPreferences();
        doReturn("1234").when(preferences).getString(Constants.Web.JSESSIONID, "");
        //when
        boolean jSessionIdInPreferences = appController.isJSessionIdInPreferences();
        //then
        assertTrue(jSessionIdInPreferences);
    }

    @Test
    public void givenJSessionIdIsEmpty() {
        //given
        doReturn(preferences).when(appController).getDefaultSharedPreferences();
        doReturn("").when(preferences).getString(Constants.Web.JSESSIONID, "");
        //when
        boolean jSessionIdInPreferences = appController.isJSessionIdInPreferences();
        //then
        assertFalse(jSessionIdInPreferences);
    }

}