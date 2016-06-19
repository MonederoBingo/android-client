package com.monederobingo.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static com.monederobingo.TestVerifiers.call;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_getCookieFromHeaders extends AppControllerSpec {

    @Test
    public void givenCookieEntryHasBothItems() {
        doReturn(Arrays.asList("cookie", "1234")).when(appController).getCookieEntry(headers);
        //when
        String cookieFromHeaders = appController.getCookieFromHeaders(headers);
        //then
        call(appController).getCookieEntry(headers);
        assertEquals("1234", cookieFromHeaders);
    }

    @Test
    public void givenCookieEntryHasOneItem() {
        doReturn(Collections.singletonList("cookie")).when(appController).getCookieEntry(headers);
        //when
        String cookieFromHeaders = appController.getCookieFromHeaders(headers);
        //then
        call(appController).getCookieEntry(headers);
        assertEquals("", cookieFromHeaders);
    }

    @Test
    public void givenCookieEntryHasNoItems() {
        doReturn(Collections.emptyList()).when(appController).getCookieEntry(headers);
        //when
        String cookieFromHeaders = appController.getCookieFromHeaders(headers);
        //then
        call(appController).getCookieEntry(headers);
        assertEquals("", cookieFromHeaders);
    }
}