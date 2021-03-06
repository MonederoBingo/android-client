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
import com.monederobingo.util.MapUtilsWrapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private static AppController instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private MapUtilsWrapper mapUtilsWrapper = new MapUtilsWrapper();

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
        if (imageLoader == null) {
            imageLoader = createImageLoader();
        }
        return imageLoader;
    }

    @NonNull
    @NotTestable
    ImageLoader createImageLoader() {
        return new ImageLoader(getRequestQueue(), new LruBitmapCache());
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

    public void putSessionCookieInPreferences(Map<String, String> headers) {
        if (shouldSetSessionCookie(headers)) {
            String cookie = getCookieFromHeaders(headers);
            putInPreferences(Constants.Web.JSESSIONID, cookie);
        }
    }

    boolean shouldSetSessionCookie(Map<String, String> headers) {
        return headers.containsKey(Constants.Web.SET_COOKIE_KEY)
                && headers.get(Constants.Web.SET_COOKIE_KEY) != null
                && headers.get(Constants.Web.SET_COOKIE_KEY).startsWith(Constants.Web.JSESSIONID);
    }

    String getCookieFromHeaders(Map<String, String> headers) {
        List<String> cookieEntry = getCookieEntry(headers);
        return cookieEntry.size() > 1 ? cookieEntry.get(1) : "";
    }

    @NonNull
    List<String> getCookieEntry(Map<String, String> headers) {
        String[] split = mapUtilsWrapper.getString(headers, Constants.Web.SET_COOKIE_KEY, "").split(";")[0].split("=");
        return Arrays.asList(split);
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
        return mapUtilsWrapper.getString(headers, Constants.Web.COOKIE_KEY, "");
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

    void putInPreferences(String key, String value) {
        SharedPreferences.Editor prefEditor = getDefaultSharedPreferences().edit();
        prefEditor.putString(key, value);
        prefEditor.apply();
    }

    void removeFromPreferences(String key) {
        SharedPreferences.Editor prefEditor = getDefaultSharedPreferences().edit();
        prefEditor.remove(key);
        prefEditor.apply();
    }

    String getFromPreferences(String key) {
        return getDefaultSharedPreferences().getString(key, "");
    }
}
