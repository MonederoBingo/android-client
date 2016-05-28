package com.monederobingo.rest;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.monederobingo.app.AppController;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class JsonObjectRequestUtil {

    private static AppController appController = AppController.getInstance();
    private static JsonObjectFactory jsonObjectFactory = new JsonObjectFactory();

    static byte[] getBytes(Map<String, String> params) {
        if (params != null) {
            JSONObject jsonObject = jsonObjectFactory.createJsonObject(params);
            return jsonObject.toString().getBytes();
        }
        return new byte[0];
    }

    static Response<JSONObject> getJsonObjectResponse(NetworkResponse response,
                                                      CustomJsonObjectRequest jsonObjectRequest) {
        appController.checkSessionCookie(response.headers);
        return jsonObjectRequest.customParseNetworkResponse(response);
    }

    static Map<String, String> getHeaders(CustomJsonObjectRequest customJsonObjectRequest,
                                          String apiKey, String userId) throws AuthFailureError {
        Map<String, String> headers = customJsonObjectRequest.customGetHeaders();
        if (headers == null
                || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<>();
        }
        appController.addSessionCookie(headers);
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

    public static void setJsonObjectFactory(JsonObjectFactory jsonObjectFactory) {
        JsonObjectRequestUtil.jsonObjectFactory = jsonObjectFactory;
    }

    public static void setAppController(AppController appController) {
        JsonObjectRequestUtil.appController = appController;
    }
}
