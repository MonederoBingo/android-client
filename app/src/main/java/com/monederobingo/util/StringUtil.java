package com.monederobingo.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {
    public static String removeExtraDecimal(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }

    public static String toUTF8String(String string) {
        try {
            return new String(string.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return string;
        }
    }
}
