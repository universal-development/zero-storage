package com.unidev.zerostorage;

import com.unidev.zerostorage.index.IndexStorage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

/**
 * Storage index tests
 */
public class TestIndexStorage {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void testIndexStorageSave() {
        IndexStorage indexStorage = new IndexStorage();

        Storage blankIndex = new Storage();
        indexStorage.index(blankIndex);

        File root = folder.getRoot();

        indexStorage.storageRoot(root);
        indexStorage.save();

    }

}
