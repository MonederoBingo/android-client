package com.monederobingo.rest;

import org.json.JSONObject;

import java.util.Map;

public class JsonObjectFactory {

    JSONObject createJsonObject(Map<String, String> params) {
        return new JSONObject(params);
    }
}
