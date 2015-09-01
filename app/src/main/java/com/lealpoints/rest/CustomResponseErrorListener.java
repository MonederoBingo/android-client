package com.lealpoints.rest;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.lealpoints.app.AppController;

public class CustomResponseErrorListener implements Response.ErrorListener {
    private final ApiListener apiListener;

    public CustomResponseErrorListener(ApiListener apiListener) {
        this.apiListener = apiListener;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        VolleyLog.d(AppController.TAG, "Error: " + volleyError.getMessage());
        apiListener.onError(volleyError);
        apiListener.stopLoading();
    }
}
