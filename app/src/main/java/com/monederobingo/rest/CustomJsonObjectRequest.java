package com.monederobingo.rest;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.monederobingo.app.AppController;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CustomJsonObjectRequest extends JsonObjectRequest {
    private final Map<String, String> params;
    private final String apiKey;
    private final String userId;

    public CustomJsonObjectRequest(int method, String url, JSONObject jsonRequest,
                                   final ApiListener apiListener, Map<String, String> params,
                                   String userId, String apiKey) {
        super(method, url, jsonRequest, new CustomResponseJsonObjectListener(apiListener, params),
                new CustomResponseErrorListener(apiListener));
        this.params = params;
        this.apiKey = apiKey;
        this.userId = userId;
    }

    @Override
    public byte[] getBody() {
        if (params != null) {
            JSONObject jsonObject = new JSONObject(params);
            return jsonObject.toString().getBytes();
        }
        return "".getBytes();
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        AppController.getInstance().checkSessionCookie(response.headers);
        return super.parseNetworkResponse(response);
    }

    /**
     * Passing some request headers
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        if (headers == null
                || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<>();
        }
        AppController.getInstance().addSessionCookie(headers);
        String displayLanguage = Locale.getDefault().getLanguage();
        headers.put("language", displayLanguage);
        if (apiKey != null && !apiKey.isEmpty()) {
            headers.put("Api-Key", apiKey);
        }
        if (userId != null && !userId.isEmpty()) {
            headers.put("User-Id", userId);
        }
        return headers;
    }
}
