package com.lealpoints.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.lealpoints.util.LruBitmapCache;

import java.util.Map;

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "JSESSIONID";
    private static final String SMS_KEY = "SmsKey";
    private static final String API_KEY = "ApiKey";
    private static final String USER_ID = "UserId";
    private static final String PHONE = "phoneNumber";
    private static AppController mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private SharedPreferences _preferences;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        _preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, Object tag) {
        // set the default tag if tag is empty
        req.setTag(tag == null ? TAG : tag);
        req.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /**
     * Checks the response headers for session cookie and saves it
     * if it finds it.
     *
     * @param headers Response Headers.
     */
    public final void checkSessionCookie(Map<String, String> headers) {
        if (headers.containsKey(SET_COOKIE_KEY)
                && headers.get(SET_COOKIE_KEY).startsWith(SESSION_COOKIE)) {
            String cookie = headers.get(SET_COOKIE_KEY);
            if (cookie.length() > 0) {
                String[] splitCookie = cookie.split(";");
                String[] splitSessionId = splitCookie[0].split("=");
                cookie = splitSessionId[1];
                SharedPreferences.Editor prefEditor = _preferences.edit();
                prefEditor.putString(SESSION_COOKIE, cookie);
                prefEditor.apply();
            }
        }
    }

    public final void addSessionCookie(Map<String, String> headers) {
        String sessionId = _preferences.getString(SESSION_COOKIE, "");
        if (sessionId.length() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(SESSION_COOKIE);
            builder.append("=");
            builder.append(sessionId);
            if (headers.containsKey(COOKIE_KEY)) {
                builder.append("; ");
                builder.append(headers.get(COOKIE_KEY));
            }
            headers.put(COOKIE_KEY, builder.toString());
        }
    }

    public void putSmsKeyInPreferences(String value) {
        putInPreferences(SMS_KEY, value);
    }

    public String getSmsKeyFromPreferences() {
        return getFromPreferences(SMS_KEY);
    }

    public void putPhoneInPreferences(String value) {
        putInPreferences(PHONE, value);
    }

    public void removePhoneFromPreferences() {
        removeFromPreferences(PHONE);
    }

    public String getPhoneFromPreferences() {
        return getFromPreferences(PHONE);
    }

    public void putApiKeyInPreferences(String apiKey) {
        putInPreferences(API_KEY, apiKey);
    }

    public String getApiKeyFromPreferences() {
        return getFromPreferences(API_KEY);
    }

    public void putUserIdInPreferences(String userId) {
        putInPreferences(USER_ID, userId);
    }

    public String getUserIdFromPreferences() {
        return getFromPreferences(USER_ID);
    }

    private void putInPreferences(String key, String value) {
        SharedPreferences.Editor prefEditor = _preferences.edit();
        prefEditor.putString(key, value);
        prefEditor.apply();
    }

    private void removeFromPreferences(String key) {
        SharedPreferences.Editor prefEditor = _preferences.edit();
        prefEditor.remove(key);
        prefEditor.apply();
    }

    private String getFromPreferences(String key) {
        return _preferences.getString(key, "");
    }
}
