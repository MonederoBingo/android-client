package com.monederobingo;

import com.monederobingo.app.AppController;

import java.lang.reflect.Field;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class TestVerifiers {

    public static <T> T call(T mock) {
        return verify(mock);
    }

    public static <T> T neverCall(T mock) {
        return verify(mock, never());
    }

    public Field getFieldFromReflection(String fieldName, AppController object) throws NoSuchFieldException, IllegalAccessException {
        Field field = AppController.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }
}
