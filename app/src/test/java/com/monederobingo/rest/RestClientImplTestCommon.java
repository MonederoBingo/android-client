package com.monederobingo.rest;

import android.support.annotation.NonNull;

import com.monederobingo.app.AppController;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.mockito.Mockito.verify;

public class RestClientImplTestCommon {

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