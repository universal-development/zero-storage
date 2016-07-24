package com.unidev.zerostorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Storage file accessing service
 */
public class Storage {

    private List<Metadata> metadata;

    public Storage() {
        metadata = new ArrayList<>();
    }

    public List<Metadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Metadata> metadata) {
        this.metadata = metadata;
    }
}
