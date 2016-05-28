package com.monederobingo.rest;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.monederobingo.app.AppController;
import com.monederobingo.model.ServiceResult;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class CustomResponseJsonObjectListener implements Response.Listener<JSONObject> {

    private final ApiListener apiListener;
    private final Map<String, String> params;

    public CustomResponseJsonObjectListener(ApiListener apiListener, Map<String, String> params) {
        this.apiListener = apiListener;
        this.params = params;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        logResponse(jsonObject);
        try {
            apiListener.stopLoading();
            ServiceResult serviceResult = parseServiceResult(jsonObject);
            apiListener.onResponse(serviceResult, params);
        } catch (Exception e) {
            logException(e);
        }
    }

    void logResponse(JSONObject jsonObject) {
        Log.d(AppController.TAG, jsonObject.toString());
    }

    void logException(Exception e) {
        VolleyLog.d(AppController.TAG, "Error: " + e.getMessage());
    }

    ServiceResult parseServiceResult(JSONObject jsonObject) throws UnsupportedEncodingException {
        boolean isSuccess = jsonObject.optBoolean("success");
        String message = jsonObject.optString("message");
        if (message != null) {
            message = new String(message.getBytes("ISO-8859-1"), "UTF-8");
        }
        String object = jsonObject.optString("object");
        return new ServiceResult(isSuccess, message, object);
    }
}
