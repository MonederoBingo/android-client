package com.monederobingo.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConstantsTest {

    @Test
    public void verifyImageUrlValue() {
        assertEquals("company/logo/", Constants.IMAGE_URL);
    }
}