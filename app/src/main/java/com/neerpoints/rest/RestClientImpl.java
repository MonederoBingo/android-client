package com.neerpoints.rest;


import com.android.volley.toolbox.JsonObjectRequest;
import com.neerpoints.app.AppController;

import java.util.HashMap;
import java.util.Map;

public class RestClientImpl implements RestClient {
    private static RestClientImpl restClientImpl = new RestClientImpl();
//        private final String BASE_URL = "http://192.168.1.67:9090/api/";
    private final String BASE_URL = "http://services-neerpoints.rhcloud.com/api/";

    private RestClientImpl() {
    }

    public static RestClientImpl getInstance() {
        return restClientImpl;
    }

    @Override
    public void callApi(int method, String path, Map<String, String> params, final ApiListener apiListener, Object tag) {
        apiListener.startLoading();
        String url = BASE_URL + path;
        params = params == null ? new HashMap<String, String>() : params;
        JsonObjectRequest req = new CustomJsonObjectRequest(method, url, null, apiListener, params);
        AppController.getInstance().addToRequestQueue(req, tag);
    }

    public void stopService(Object tag) {
        AppController.getInstance().cancelPendingRequests(tag);
    }


}
