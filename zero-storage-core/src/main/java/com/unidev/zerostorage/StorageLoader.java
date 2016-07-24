package com.unidev.zerostorage;

import java.io.File;
import java.io.IOException;

/**
 * Loader of storage file
 */
public class StorageLoader {

    private File file;


    public StorageLoader() {}

    public StorageLoader(File file) { this.file = file; }

    public static StorageLoader storageLoader() {return new StorageLoader();}

    public StorageLoader file(File file) {
        this.file = file;
        return this;
    }

    public Storage load() {
        try {
            return StorageUtils.STORAGE_OBJECT_MAPPER.readValue(file, Storage.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageException(e);
        }
    }

}
