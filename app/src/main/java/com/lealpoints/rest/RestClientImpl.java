package com.lealpoints.rest;


import com.android.volley.toolbox.JsonObjectRequest;
import com.lealpoints.app.AppController;

import java.util.HashMap;
import java.util.Map;

import lealpoints.com.frontend_android.BuildConfig;

public class RestClientImpl implements RestClient {
    private static RestClientImpl restClientImpl = new RestClientImpl();

    private RestClientImpl() {
    }

    public static RestClientImpl getInstance() {
        return restClientImpl;
    }

    @Override
    public void callApi(int method, String path, Map<String, String> params, final ApiListener apiListener, Object tag) {
        apiListener.startLoading();
        String url = BuildConfig.API_URL + "api/v1/" + path;
        params = params == null ? new HashMap<String, String>() : params;
        AppController appController = AppController.getInstance();
        String userId = appController.getUserIdFromPreferences();
        String apiKey = appController.getApiKeyFromPreferences();
        JsonObjectRequest req = new CustomJsonObjectRequest(method, url, null, apiListener, params, userId, apiKey);
        appController.addToRequestQueue(req, tag);
    }

    @Override
    public void callAuth(int method, String path, Map<String, String> params, final ApiListener apiListener, Object tag) {
        apiListener.startLoading();
        String url = BuildConfig.API_URL + path;
        params = params == null ? new HashMap<String, String>() : params;
        JsonObjectRequest req = new CustomJsonObjectRequest(method, url, null, apiListener, params, null, null);
        AppController.getInstance().addToRequestQueue(req, tag);
    }

    public void stopService(Object tag) {
        AppController.getInstance().cancelPendingRequests(tag);
    }


}
