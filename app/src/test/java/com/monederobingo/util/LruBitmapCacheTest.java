package com.monederobingo.util;

import android.graphics.Bitmap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class LruBitmapCacheTest {

    @Mock
    Bitmap bitmap;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getDefaultLruCacheSize_shouldReturnPositive() {
        assertTrue(LruBitmapCache.getDefaultLruCacheSize() > 0);
    }

    @Test
    public void sizeOf_shouldReturn1024() {
        LruBitmapCache lruBitmapCache = new LruBitmapCache();
        when(bitmap.getRowBytes()).thenReturn(1024);
        when(bitmap.getHeight()).thenReturn(1024);
        int sizeOf = lruBitmapCache.sizeOf("", bitmap);
        assertEquals(1024, sizeOf);
    }

    @Test
    public void getBitmap_shouldNotReturnNull() {
        LruBitmapCache lruBitmapCache = new LruBitmapCache();
        lruBitmapCache.put("key", bitmap);
        Bitmap bitmapFromCache = lruBitmapCache.getBitmap("key");
        assertNotNull(bitmapFromCache);
    }

    @Test
    public void getBitmap_shouldReturnSameAsPut() {
        LruBitmapCache lruBitmapCache = new LruBitmapCache();
        lruBitmapCache.put("key", bitmap);
        Bitmap bitmapFromCache = lruBitmapCache.getBitmap("key");
        assertEquals(bitmap, bitmapFromCache);
    }

    @Test
    public void putBitmap_shouldReturnSameAsGet() {
        LruBitmapCache lruBitmapCache = new LruBitmapCache();
        lruBitmapCache.putBitmap("key", bitmap);
        Bitmap bitmapFromCache = lruBitmapCache.get("key");
        assertEquals(bitmap, bitmapFromCache);
    }
}