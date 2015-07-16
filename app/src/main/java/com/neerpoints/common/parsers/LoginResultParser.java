package com.neerpoints.common.parsers;

import com.neerpoints.model.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginResultParser {

    public static LoginResult getLoginResult(String object) throws JSONException {
        JSONObject jsonObject = new JSONObject(object);
        String clientUserId = jsonObject.optString("clientUserId");
        String apiKey = jsonObject.optString("apiKey");
        return new LoginResult(clientUserId, apiKey);
    }
}
