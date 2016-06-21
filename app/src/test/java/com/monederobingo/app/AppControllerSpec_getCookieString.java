package com.monederobingo.app;

import com.monederobingo.common.Constants;

import org.junit.Test;

import static com.monederobingo.ClassMembersAccessor.setFieldValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

public class AppControllerSpec_getCookieString extends AppControllerSpec {

    @Test
    public void givenHeadersContainsCookieKey() {
        //given
        setFieldValue(appController, "mapUtilsWrapper", mapUtilsWrapper);
        doReturn("").when(mapUtilsWrapper).getString(headers, Constants.Web.COOKIE_KEY, "");
        //when
        String cookieString = appController.getCookieString(headers);
        //then
        assertEquals("", cookieString);
    }
}