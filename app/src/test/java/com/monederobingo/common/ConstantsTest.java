package com.monederobingo.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstantsTest {

    @Test
    public void verifyPreferencesConstants() {
        assertEquals("phoneNumber", Constants.Preferences.PHONE_NUMBER);
        assertEquals("SmsKey", Constants.Preferences.SMS_KEY);
        assertEquals("ApiKey", Constants.Preferences.API_KEY);
        assertEquals("UserId", Constants.Preferences.USER_ID);
    }

    @Test
    public void verifyWebConstants() {
        assertEquals("company/logo/", Constants.Web.IMAGE_URL);
        assertEquals("Set-Cookie", Constants.Web.SET_COOKIE_KEY);
        assertEquals("JSESSIONID", Constants.Web.JSESSIONID);
        assertEquals("Cookie", Constants.Web.COOKIE_KEY);
    }
}