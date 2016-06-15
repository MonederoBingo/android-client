package com.monederobingo;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class TestVerifiers {

    public static <T> T call(T mock) {
        return verify(mock);
    }

    public static <T> T neverCall(T mock) {
        return verify(mock, never());
    }
}
