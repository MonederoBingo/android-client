package com.monederobingo.common.parsers;

import com.monederobingo.factories.JSONObjectFactory;
import com.monederobingo.model.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginResultParser {

    private JSONObjectFactory jsonObjectFactory;

    public LoginResultParser(JSONObjectFactory jsonObjectFactory) {
        this.jsonObjectFactory = jsonObjectFactory;
    }

    public LoginResult getLoginResult(String object) throws JSONException {
        JSONObject jsonObject = jsonObjectFactory.createJSONObject(object);
        String clientUserId = jsonObject.optString("clientUserId");
        String apiKey = jsonObject.optString("apiKey");
        return new LoginResult(clientUserId, apiKey);
    }
}
