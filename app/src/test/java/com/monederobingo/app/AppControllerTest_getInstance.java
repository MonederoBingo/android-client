package com.monederobingo.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppControllerTest_getInstance {

    @Test
    public void shouldReturnNull() throws Exception {
        assertNull(AppController.getInstance());
    }
}