package com.neerpoints.rest;

import com.android.volley.VolleyError;
import com.neerpoints.model.ServiceResult;

import java.util.Map;

public interface ApiListener {

    public void onResponse(ServiceResult serviceResult, Map<String, String> requestParams);

    public void onError(VolleyError volleyError);

    void startLoading();

    void stopLoading();
}
