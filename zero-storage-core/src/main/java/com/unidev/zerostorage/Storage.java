package com.unidev.zerostorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Storage file accessing service
 */
public class Storage {

    private Metadata details;
    private List<Metadata> metadata;

    public Storage() {
        metadata = new ArrayList<>();
        details = new Metadata();
    }

    /**
     * Add metadata to list
     * @param metadata
     * @return
     */
    public Storage addMetadata(Metadata metadata) {
        this.metadata.add(metadata);
        return this;
    }

    /**
     * Add metadata record as first record in list
     * @param metadata
     * @return
     */
    public Storage addMetadateFirst(Metadata metadata) {
        this.metadata.add(0, metadata);
        return this;
    }

    public Metadata details() {
        return this.details;
    }

    public List<Metadata> metadata() {
        return this.metadata;
    }

    public List<Metadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Metadata> metadata) {
        this.metadata = metadata;
    }

    public Metadata getDetails() {
        return details;
    }

    public void setDetails(Metadata details) {
        this.details = details;
    }
}
