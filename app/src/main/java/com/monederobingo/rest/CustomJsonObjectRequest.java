package com.monederobingo.rest;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

public class CustomJsonObjectRequest extends JsonObjectRequest {
    private final Map<String, String> params;
    private final String apiKey;
    private final String userId;

    private CustomJsonObjectRequest(int method, String url, JSONObject jsonRequest,
                                    final ApiListener apiListener, Map<String, String> params,
                                    String userId, String apiKey) {
        super(method, url, jsonRequest, new CustomResponseJsonObjectListener(apiListener, params),
                new CustomResponseErrorListener(apiListener));
        this.params = params;
        this.apiKey = apiKey;
        this.userId = userId;
    }

    public static CustomJsonObjectRequest createRequest(int method, String url, JSONObject jsonRequest,
                                                 final ApiListener apiListener, Map<String, String> params,
                                                 String userId, String apiKey) {
        return new CustomJsonObjectRequest(method, url, null, apiListener, params, userId, apiKey);
    }

    @Override
    public byte[] getBody() {
        return JsonObjectRequestUtil.getBytes(params);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        return JsonObjectRequestUtil.getJsonObjectResponse(response, this);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return JsonObjectRequestUtil.getHeaders(this, apiKey, userId);
    }

    Response<JSONObject> customParseNetworkResponse(NetworkResponse networkResponse) {
        return super.parseNetworkResponse(networkResponse);
    }

    Map<String, String> customGetHeaders() throws AuthFailureError {
        return super.getHeaders();
    }
}
