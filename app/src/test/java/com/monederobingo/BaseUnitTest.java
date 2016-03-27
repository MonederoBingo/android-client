package com.monederobingo;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class BaseUnitTest {
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
