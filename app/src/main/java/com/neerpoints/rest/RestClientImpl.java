package com.neerpoints.rest;


import com.android.volley.toolbox.JsonObjectRequest;
import com.neerpoints.app.AppController;

import java.util.HashMap;
import java.util.Map;

public class RestClientImpl implements RestClient {
    private static RestClientImpl restClientImpl = new RestClientImpl();
//        private final String API_URL = "http://192.168.1.67:9090/api/";
//        private final String AUTH_URL = "http://192.168.1.67:9090/auth/";
    private final String API_URL = "http://services.lealpoints.com/api";
    private final String AUTH_URL = "http://services.lealpoints.com/auth";

    private RestClientImpl() {
    }

    public static RestClientImpl getInstance() {
        return restClientImpl;
    }

    @Override
    public void callApi(int method, String path, Map<String, String> params, final ApiListener apiListener, Object tag) {
        apiListener.startLoading();
        String url = API_URL + path;
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
        String url = AUTH_URL + path;
        params = params == null ? new HashMap<String, String>() : params;
        JsonObjectRequest req = new CustomJsonObjectRequest(method, url, null, apiListener, params, null, null);
        AppController.getInstance().addToRequestQueue(req, tag);
    }

    public void stopService(Object tag) {
        AppController.getInstance().cancelPendingRequests(tag);
    }


}
