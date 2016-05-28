package com.monederobingo.rest;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.monederobingo.app.AppController;

public class CustomResponseErrorListener implements Response.ErrorListener {
    private final ApiListener apiListener;

    public CustomResponseErrorListener(ApiListener apiListener) {
        this.apiListener = apiListener;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        if(volleyError == null) {
            throw new RuntimeException("volleyError should not be null");
        }
        logMessage(volleyError);
        apiListener.onError(volleyError);
        apiListener.stopLoading();
    }

    void logMessage(VolleyError volleyError) {
        VolleyLog.d(AppController.TAG, "Error: " + volleyError.getMessage());
    }
}
