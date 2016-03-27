package com.monederobingo.rest;


import android.support.annotation.NonNull;

import com.android.volley.toolbox.JsonObjectRequest;
import com.monederobingo.app.AppController;
import com.monederobingo.client.BuildConfig;

import java.util.HashMap;
import java.util.Map;

public class RestClientImpl implements RestClient {
    private final AppController appController;
    private static RestClientImpl restClientImpl = new RestClientImpl(AppController.getInstance());

    RestClientImpl(AppController appController) {
        this.appController = appController;
    }

    public static RestClientImpl getInstance() {
        return restClientImpl;
    }

    @Override
    public void callApi(int method, String path, Map<String, String> params,
                        final ApiListener apiListener, Object tag) {
        apiListener.startLoading();
        String url = BuildConfig.API_URL + "api/v1/" + path;
        params = params == null ? new HashMap<String, String>() : params;
        String userId = appController.getUserIdFromPreferences();
        String apiKey = appController.getApiKeyFromPreferences();
        JsonObjectRequest req = createRequest(method, url, apiListener, params, userId, apiKey);
        appController.addToRequestQueue(req, tag);
    }

    @NonNull
    CustomJsonObjectRequest createRequest(int method, String url, ApiListener apiListener, Map<String, String> params,
                                          String userId, String apiKey) {
        return CustomJsonObjectRequest.createRequest(method, url, null, apiListener,
                params, userId, apiKey);
    }

    @Override
    public void callAuth(int method, String path, Map<String, String> params, final ApiListener apiListener, Object tag) {
        apiListener.startLoading();
        String url = BuildConfig.API_URL + path;
        params = params == null ? new HashMap<String, String>() : params;
        JsonObjectRequest req = createRequest(method, url, apiListener, params, null, null);
        appController.addToRequestQueue(req, tag);
    }

    public void stopService(Object tag) {
        appController.cancelPendingRequests(tag);
    }
}
