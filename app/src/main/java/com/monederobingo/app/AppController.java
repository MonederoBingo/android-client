package com.monederobingo.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.monederobingo.common.Constants;
import com.monederobingo.util.LruBitmapCache;

import java.util.Map;

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private static AppController instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public static synchronized AppController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        callOnCreateInSuper();
        instance = this;
    }

    @NotTestable
    void callOnCreateInSuper() {
        super.onCreate();
    }

    @NotTestable
    SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }

    @NotTestable
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = createVolleyRequestQueue();
        }
        return requestQueue;
    }

    @NonNull
    @NotTestable
    RequestQueue createVolleyRequestQueue() {
        return Volley.newRequestQueue(getApplicationContext());
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (imageLoader == null) {
            imageLoader = createImageLoader();
        }
        return this.imageLoader;
    }

    @NonNull
    @NotTestable
    ImageLoader createImageLoader() {
        return new ImageLoader(this.requestQueue, new LruBitmapCache());
    }

    public <T> void addToRequestQueue(Request<T> req, Object tag) {
        req.setTag(getNonNullTag(tag));
        req.setRetryPolicy(getRetryPolicy());
        getRequestQueue().add(req);
    }

    Object getNonNullTag(Object tag) {
        return tag == null ? TAG : tag;
    }

    @NonNull
    @NotTestable
    DefaultRetryPolicy getRetryPolicy() {
        return new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    public void checkSessionCookie(Map<String, String> headers) {
        if (headers.containsKey(Constants.Web.SET_COOKIE_KEY)
                && headers.get(Constants.Web.SET_COOKIE_KEY).startsWith(Constants.Web.JSESSIONID)) {
            String cookie = headers.get(Constants.Web.SET_COOKIE_KEY);
            if (cookie.length() > 0) {
                String[] splitCookie = cookie.split(";");
                String[] splitSessionId = splitCookie[0].split("=");
                cookie = splitSessionId[1];
                SharedPreferences.Editor prefEditor = getDefaultSharedPreferences().edit();
                prefEditor.putString(Constants.Web.JSESSIONID, cookie);
                prefEditor.apply();
            }
        }
    }

    public void addSessionCookie(Map<String, String> headers) {
        if (isJSessionIdInPreferences()) {
            String cookie = getSessionIdCookieString() + getCookieString(headers);
            headers.put(Constants.Web.COOKIE_KEY, cookie);
        }
    }

    boolean isJSessionIdInPreferences() {
        return getDefaultSharedPreferences().getString(Constants.Web.JSESSIONID, "").length() > 0;
    }

    @NonNull
    String getSessionIdCookieString() {
        return Constants.Web.JSESSIONID + "=" + getDefaultSharedPreferences().getString(Constants.Web.JSESSIONID, "");
    }

    String getCookieString(Map<String, String> headers) {
        String cookieString = "";
        if (headers.containsKey(Constants.Web.COOKIE_KEY)) {
            cookieString =  "; " + headers.get(Constants.Web.COOKIE_KEY);
        }
        return cookieString;
    }

    public void putSmsKeyInPreferences(String value) {
        putInPreferences(Constants.Preferences.SMS_KEY, value);
    }

    public String getSmsKeyFromPreferences() {
        return getFromPreferences(Constants.Preferences.SMS_KEY);
    }

    public void putPhoneInPreferences(String value) {
        putInPreferences(Constants.Preferences.PHONE_NUMBER, value);
    }

    public void removePhoneFromPreferences() {
        removeFromPreferences(Constants.Preferences.PHONE_NUMBER);
    }

    public String getPhoneFromPreferences() {
        return getFromPreferences(Constants.Preferences.PHONE_NUMBER);
    }

    public void putApiKeyInPreferences(String apiKey) {
        putInPreferences(Constants.Preferences.API_KEY, apiKey);
    }

    public String getApiKeyFromPreferences() {
        return getFromPreferences(Constants.Preferences.API_KEY);
    }

    public void putUserIdInPreferences(String userId) {
        putInPreferences(Constants.Preferences.USER_ID, userId);
    }

    public String getUserIdFromPreferences() {
        return getFromPreferences(Constants.Preferences.USER_ID);
    }

    private void putInPreferences(String key, String value) {
        SharedPreferences.Editor prefEditor = getDefaultSharedPreferences().edit();
        prefEditor.putString(key, value);
        prefEditor.apply();
    }

    private void removeFromPreferences(String key) {
        SharedPreferences.Editor prefEditor = getDefaultSharedPreferences().edit();
        prefEditor.remove(key);
        prefEditor.apply();
    }

    private String getFromPreferences(String key) {
        return getDefaultSharedPreferences().getString(key, "");
    }
}
