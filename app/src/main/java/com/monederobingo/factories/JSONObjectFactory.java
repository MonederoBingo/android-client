package com.monederobingo.factories;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONObjectFactory {

    public JSONObject createJSONObject(String object) throws JSONException {
        return new JSONObject(object);
    }
}
