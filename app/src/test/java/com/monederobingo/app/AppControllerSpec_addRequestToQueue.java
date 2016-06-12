package com.monederobingo.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerSpec_addRequestToQueue extends AppControllerSpec {

    @Test
    public void spec() throws Exception {
        //when
        appController.addToRequestQueue(request, tag);
        //then
        shouldCallGetNonNullTag();
        shouldCallSetTagOnRequestParameter();
        shouldCallGetRetryPolicy();
        shouldCallSetRetryPolicyOnRequestParameter();
        shouldCallGetRequestQueue();
        shouldCallAddOnRequestQueue();
    }
}