package com.unidev.zerostorage;

import java.io.File;

/**
 * Storage file accessing service
 */
public class Storage {

    private File file;

    public Storage file(File file) {
        this.file = file;
        return this;
    }


    public Storage load() {
        return null;
    }


}
