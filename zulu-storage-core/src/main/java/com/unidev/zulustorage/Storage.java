package com.unidev.zulustorage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Storage file accessing service
 */
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

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
    public Storage addMetadataFirst(Metadata metadata) {
        this.metadata.add(0, metadata);
        return this;
    }

    public Optional<Metadata> fetchMetaById(String id) {
        return metadata.stream().filter( meta -> id.equals(meta._id())).findFirst();
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
