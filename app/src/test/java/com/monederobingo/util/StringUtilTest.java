package com.monederobingo.util;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StringUtilTest {

    @Test
    public void removeExtraDecimal_shouldRemove() throws Exception {
        assertEquals("10", StringUtil.removeExtraDecimal(10.0));
    }

    @Test
    public void removeExtraDecimal_shouldNotRemove() throws Exception {
        assertEquals("10.02", StringUtil.removeExtraDecimal(10.02));
    }

    @Test
    public void toUTF8String_shouldReturnAccents() throws Exception {
        assertEquals("fábrica", StringUtil.toUTF8String("fÃ¡brica"));
        assertEquals("género", StringUtil.toUTF8String("gÃ©nero"));
        assertEquals("latín", StringUtil.toUTF8String("latÃ­n"));
        assertEquals("camión", StringUtil.toUTF8String("camiÃ³n"));
        assertEquals("betún", StringUtil.toUTF8String("betÃºn"));
        assertEquals("cañon", StringUtil.toUTF8String("caÃ±on"));
        assertEquals("FÁBRICA", StringUtil.toUTF8String("FÃBRICA"));
        assertEquals("GÉNERO", StringUtil.toUTF8String("GÃNERO"));
        assertEquals("LATÍN", StringUtil.toUTF8String("LATÃN"));
        assertEquals("CAMIÓN", StringUtil.toUTF8String("CAMIÃN"));
        assertEquals("BETÚN", StringUtil.toUTF8String("BETÃN"));
        assertEquals("CAÑON", StringUtil.toUTF8String("CAÃON"));
    }
}