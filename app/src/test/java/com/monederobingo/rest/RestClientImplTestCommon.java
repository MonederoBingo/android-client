package com.monederobingo.rest;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.monederobingo.BaseUnitTest;
import com.monederobingo.app.AppController;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;

public class RestClientImplTestCommon extends BaseUnitTest {

    static RestClientImpl createRestClientImpl(AppController appController,
                                                       final CustomJsonObjectRequest customJsonObjectRequest) {
        return new RestClientImpl(appController) {
            @NonNull
            @Override
            CustomJsonObjectRequest createRequest(int method, String url, ApiListener apiListener,
                                                  Map<String, String> params, String userId, String apiKey) {
                return customJsonObjectRequest;
            }
        };
    }
}