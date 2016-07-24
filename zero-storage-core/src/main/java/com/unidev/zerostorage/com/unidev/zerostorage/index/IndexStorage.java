package com.unidev.zerostorage.com.unidev.zerostorage.index;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage index file
 */
public class IndexStorage {

    private File storageRoot;
    private List<String> storageFiles;

    public IndexStorage() {
        storageFiles = new ArrayList<>();
    }

    public IndexStorage storageRoot(File file) {
        this.storageRoot = file;
        return this;
    }


    public void loadIndex() {

    }

}
