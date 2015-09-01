package com.lealpoints.rest;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.lealpoints.model.ServiceResult;

import java.util.Map;

public class ApiAdapter implements ApiListener {

    protected final Context context;

    public ApiAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(ServiceResult serviceResult, Map<String, String> requestParams) {

    }

    @Override
    public void onError(VolleyError volleyError) {
        String message = volleyError.getMessage() == null ? "There was an error, please try again..." : volleyError.getMessage();
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    protected String getTranslation(int id) {
        return context.getResources().getString(id);
    }
}
