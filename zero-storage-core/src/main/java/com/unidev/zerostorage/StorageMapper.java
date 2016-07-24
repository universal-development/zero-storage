package com.unidev.zerostorage;

import java.io.*;

/**
 * Storage mapper object, loading and storing storages
 */
public class StorageMapper {

    private InputStream loadSource;
    private OutputStream saveSource;

    public StorageMapper() {}

    public static StorageMapper storageMapper() {return new StorageMapper();}

    public StorageMapper loadSource(InputStream inputStream) {
        this.loadSource = inputStream;
        return this;
    }

    public StorageMapper saveSource(OutputStream saveSource) {
        this.saveSource = saveSource;
        return this;
    }

    public StorageMapper loadSource(File file) {
        try {
            this.loadSource = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new StorageException(e);
        }
        return this;
    }


    public Storage load() {
        try {
            return StorageUtils.STORAGE_OBJECT_MAPPER.readValue(loadSource, Storage.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageException(e);
        }
    }

    public void save(Storage storage) {
        try {
            StorageUtils.STORAGE_OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(saveSource, storage);
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageException(e);
        }
    }


}
