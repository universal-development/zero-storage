package com.unidev.zulustorage;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import static com.unidev.zulustorage.StorageMapper.*;


/**
 * Storage loader from asset files
 */
public class AssetStorageLoader {

    /**
     * Load
     * @param path
     * @return
     */
    public static Storage load(Context context, String path) {
        AssetManager assets = context.getAssets();
        try (InputStream inputStream = assets.open(path)){
            return storageMapper().loadSource(inputStream).load();
        } catch (IOException e) {
            Log.e("AssetStorageLoader", "Failed to load storage from " + path);
            e.printStackTrace();
        }
    }

}
