package com.monederobingo.util;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

public class MapUtilsWrapper {

    public <K> String getString(final Map<? super K, ?> map, final K key, String defaultValue) {
        return MapUtils.getString(map, key, defaultValue);
    }
}
