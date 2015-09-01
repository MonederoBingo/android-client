package com.lealpoints.model;

public class LoginResult {
    private final String userId;
    private final String apiKey;

    public LoginResult(String userId, String apiKey) {
        this.userId = userId;
        this.apiKey = apiKey;
    }

    public String getUserId() {
        return userId;
    }

    public String getApiKey() {
        return apiKey;
    }
}
