package com.unidev.zulustorage.index;

import com.unidev.zulustorage.Metadata;
import com.unidev.zulustorage.Storage;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static com.unidev.zulustorage.StorageMapper.storageMapper;

/**
 * Index storage for metadata files <br/>
 * Index manage index.json file which have links to rest of the storages
 *
 */
public class IndexStorage implements Serializable {

    private static final long serialVersionUID = 362498820763181265L;

    public static final String INDEX_FILE = "index.json";
    public static final String FILE_EXT = ".json";

    public static final Integer META_PER_STORAGE = 50;

    public static final String STORAGE_RECORDS_KEY = "storage_records_";
    public static final String META_PER_STORAGE_KEY = "meta_per_storage";

    private File storageRoot;
    private File indexFile;
    private Storage index;

    public IndexStorage() {
        index = new Storage();
    }

    /**
     * Load from index from root file
     */
    public IndexStorage load() {
        if (indexFile.exists()) {
            index = storageMapper().loadSource(indexFile).load();
        } else {
            index = new Storage();
        }
        return this;
    }

    /**
     * Save index state
     */
    public void save() {
        storageMapper().saveSource(indexFile).save(index);
    }

    /**
     * Change index root
     */
    public IndexStorage storageRoot(File file) {
        this.storageRoot = file;
        this.indexFile = new File(storageRoot, INDEX_FILE);
        return this;
    }

    /**
     * Fetch metadata list
     * @return
     */
    public List<Metadata> storageFiles() {
        return index.metadata();
    }

    /**
     * Add metadata to storage - metadata will be stored in appropriate storage and logged in storage index
     * @param metadata Metadata to add
     * @return
     */
    public IndexStorage addMetadata(Metadata metadata) {
        if (index.metadata().isEmpty()) {
            addNextStorageFile();
        }

        Integer recordsPerStorage = index.details().opt(META_PER_STORAGE_KEY, META_PER_STORAGE);
        String storageFileName = index.metadata().get(0).getLink();
        int recordCount = index.details().opt(STORAGE_RECORDS_KEY + storageFileName, 0);

        if (recordCount >= recordsPerStorage) {
            addNextStorageFile();
            storageFileName = index.metadata().get(0).getLink();
        }

        File storageFile = new File(storageRoot, storageFileName);
        Storage storage = storageMapper().loadSource(storageFile).load();
        storage.addMetadataFirst(metadata);
        storageMapper().saveSource(storageFile).save(storage);

        index.details().put(STORAGE_RECORDS_KEY + storageFileName, storage.metadata().size());
        save();

        return this;
    }

    /**
     * Fetch storage by metadata id
     * @return storage object or null empty optional
     */
    public Optional<Storage> storage(String metaId) {
        Optional<Metadata> metadata = index.fetchMetaById(metaId);
        if (!metadata.isPresent()) {
            return Optional.empty();
        }
        File storageFile = new File(storageRoot, metadata.get()._id());
        return Optional.of(storageMapper().loadSource(storageFile).load());
    }


    private IndexStorage addNextStorageFile() {
        int fileCount = index.metadata().size();
        File metadataFile = new File(storageRoot, (fileCount + 1) + FILE_EXT);

        Storage storage = new Storage();
        storageMapper().saveSource(metadataFile).save(storage);

        Metadata indexMetadata = new Metadata();
        indexMetadata.setLink(metadataFile.getName());
        indexMetadata.set_id(metadataFile.getName());
        index.addMetadataFirst(indexMetadata);
        save();

        return this;
    }

    public IndexStorage index(Storage index) {
        this.index = index;
        return this;
    }

    public Storage index() {
        return index;
    }

    public Storage getIndex() {
        return this.index;
    }

    public void setIndex(Storage index) {
        this.index = index;
    }

}
