package com.unidev.zulustorage;

import java.util.HashMap;

/**
 * Storage metadata entry
 */
public class Metadata extends HashMap<String, Object> {

    public static Metadata newMetadata() {return new Metadata(); }

    /**
     * Fetch metadata by key, if value is missing, null is returned
     * @param key Metadata key
     * @return value by key or null
     */
    public <T> T opt(String key) {
        if (!super.containsKey(key)) {
            return null;
        }
        return (T) get(key);
    }

    /**
     * Return default by key, if value is missing, defaultValue is returned
     * @param key
     * @param defaultValue
     * @param <T>
     * @return
     */
    public <T> T opt(String key, T defaultValue) {
        if (!super.containsKey(key)) {
            return defaultValue;
        }
        return (T) get(key);
    }

    public String link() {
        return opt("link");
    }

    public String _id() {
        return opt("_id");
    }

    public Metadata _id(String id) {
        put("_id", id);
        return this;
    }

    public Metadata link(String link) {
        put("link", link);
        return this;
    }



    public String get_id() {
        return opt("_id");
    }

    public void set_id(String _id) {
        put("_id", _id);
    }

    public String getLink() {
        return opt("link");
    }

    public void setLink(String link) {
        put("link", link);
    }

}
