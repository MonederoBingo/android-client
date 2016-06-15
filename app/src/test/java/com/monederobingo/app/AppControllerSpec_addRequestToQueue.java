package com.monederobingo.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.monederobingo.TestVerifiers.call;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_addRequestToQueue extends AppControllerSpec {

    @Test
    public void spec() throws Exception {
        //when
        appController.addToRequestQueue(request, tag);
        //then
        call(appController).getNonNullTag(tag);
        call(request).setTag(tag);
        call(appController).getRetryPolicy();
        call(request).setRetryPolicy(retryPolicy);
        call(appController).getRequestQueue();
        call(requestQueue).add(request);
    }
}