package com.monederobingo;

import android.annotation.TargetApi;
import android.os.Build;

import com.monederobingo.app.AppController;

import java.lang.reflect.Field;

import static org.mockito.Mockito.verify;

public class ClassMembersAccessor {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static Object getFieldValue(Object object, String fieldName) {
        Object value;
        try {
            value = getValue(object, fieldName);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    private static Object getValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Object value;
        Field field = AppController.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        value = field.get(object);
        return value;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void setFieldValue(Object object, String fieldName, Object value) {
        try {
            Field field = AppController.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
