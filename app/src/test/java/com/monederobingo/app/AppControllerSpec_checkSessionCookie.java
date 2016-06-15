package com.monederobingo.app;

import com.monederobingo.TestVerifiers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;
import static com.monederobingo.TestVerifiers.call;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_checkSessionCookie extends AppControllerSpec {

    @Test
    public void givenShouldSetSessionCookieIsTrue() {
        //given
        doReturn(true).when(appController).shouldSetSessionCookie(headers);
        //when
        appController.checkSessionCookie(headers);
        //then
        call(appController).getCookieFromHeaders(headers);
    }


}