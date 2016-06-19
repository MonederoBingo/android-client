package com.monederobingo.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AppControllerSpec_getCookieEntry extends AppControllerSpec {
    private List<String> expectedResult;

    public AppControllerSpec_getCookieEntry(Map<String, String> headers, List<String> expectedResult) {
        this.headers = headers;
        this.expectedResult = expectedResult;
    }

    @Test
    public void spec() {
        //when
        List<String> cookieEntry = appController.getCookieEntry(headers);
        //then
        assertEquals(expectedResult.size(), cookieEntry.size());
        assertCookiesAreEqual(cookieEntry);
    }

    private void assertCookiesAreEqual(List<String> cookieEntry) {
        for (int i = 0; i < expectedResult.size(); i++) {
            assertEquals(expectedResult.get(i), cookieEntry.get(i));
        }
    }

    @Parameterized.Parameters
    public static Collection headerFixtures() {
        return Arrays.asList(
                new Object[]{singletonMap("Set-Cookie", "cookie=1234;foe"), Arrays.asList("cookie", "1234")},
                new Object[]{singletonMap("Set-Cookie", "cookie=1234"), Arrays.asList("cookie", "1234")},
                new Object[]{singletonMap("Set-Cookie", "cookie="), Collections.singletonList("cookie")},
                new Object[]{singletonMap("Set-Cookie", ""), Collections.singletonList("")},
                new Object[]{singletonMap("Set-Cookie", null), Collections.singletonList("")},
                new Object[]{singletonMap("", null), Collections.singletonList("")},
                new Object[]{singletonMap(null, null), Collections.singletonList("")}
        );
    }
}