package com.lealpoints.rest;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.lealpoints.app.AppController;
import com.lealpoints.model.ServiceResult;

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
        Log.d(AppController.TAG, jsonObject.toString());
        try {
            apiListener.stopLoading();
            ServiceResult serviceResult = parseServiceResult(jsonObject);
            apiListener.onResponse(serviceResult, params);
        } catch (Exception e) {
            VolleyLog.d(AppController.TAG, "Error: " + e.getMessage());
        }
    }

    private ServiceResult parseServiceResult(JSONObject jsonObject) {
        boolean isSuccess = jsonObject.optBoolean("success");
        String message;
        try {
            message = new String(jsonObject.optString("message").getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            message = jsonObject.optString("message");
        }
        String object = jsonObject.optString("object");
        return new ServiceResult(isSuccess, message, object);
    }
}
