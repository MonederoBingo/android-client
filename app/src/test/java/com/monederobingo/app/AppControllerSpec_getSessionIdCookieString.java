package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_getSessionIdCookieString extends AppControllerSpec {

    @Test
    public void spec() {
        //given
        doReturn(preferences).when(appController).getDefaultSharedPreferences();
        doReturn("1234").when(preferences).getString(Constants.Web.JSESSIONID, "");
        //when
        String sessionIdCookieString = appController.getSessionIdCookieString();
        //then
        assertEquals(Constants.Web.JSESSIONID + "=1234", sessionIdCookieString);
    }
}