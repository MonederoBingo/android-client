package com.monederobingo.rest;

import com.android.volley.VolleyError;
import com.monederobingo.model.ServiceResult;

import java.util.Map;

public interface ApiListener {

    public void onResponse(ServiceResult serviceResult, Map<String, String> requestParams);

    public void onError(VolleyError volleyError);

    void startLoading();

    void stopLoading();
}
