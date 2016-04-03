package com.monederobingo.rest;

import java.util.Map;

public interface RestClient {

    void callApi(int method, final String path, Map<String, String> params, final ApiListener apiListener, Object tag);
    void callAuth(int method, final String path, Map<String, String> params, final ApiListener apiListener, Object tag);
    void stopService(Object tag);
}
