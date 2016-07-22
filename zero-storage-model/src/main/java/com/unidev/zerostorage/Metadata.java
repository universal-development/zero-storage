package com.unidev.zerostorage;

import java.util.HashMap;

/**
 * Storage metadata entry
 */
public class Metadata extends HashMap<String, Object> {

    /**
     * Fetch metadata by key, if value is missing, null is returned
     * @param key Metadata key
     * @return value by key or null
     */
    public Object opt(String key) {
        if (!super.containsKey(key)) {
            return null;
        }
        return get(key);
    }


}
