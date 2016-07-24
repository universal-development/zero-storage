package com.unidev.zerostorage;

import java.io.*;

/**
 * Loader of storage file
 */
public class StorageLoader {

    private InputStream inputStream;

    public StorageLoader() {}

    public StorageLoader(InputStream inputStream) { this.inputStream  = inputStream; }

    public static StorageLoader storageLoader() {return new StorageLoader();}

    public StorageLoader source(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public StorageLoader source(File file) {
        try {
            this.inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new StorageException(e);
        }
        return this;
    }


    public Storage load() {
        try {
            return StorageUtils.STORAGE_OBJECT_MAPPER.readValue(inputStream, Storage.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageException(e);
        }
    }

}
