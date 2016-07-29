package com.unidev.zerostorage;

import com.unidev.zerostorage.index.IndexStorage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Storage index tests
 */
public class TestIndexStorage {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void testBlankIndexStorageSave() {
        IndexStorage indexStorage = new IndexStorage();

        Storage blankIndex = new Storage();
        indexStorage.index(blankIndex);

        File root = folder.getRoot();

        indexStorage.storageRoot(root);
        indexStorage.save();

    }

    @Test
    public void testIndexStorageSave() {
        IndexStorage indexStorage = new IndexStorage();

        Storage index = new Storage();
        index.details().put("Potato", "Tomato");
        index.details().put("Tomato", null);
        indexStorage.index(index);

        File root = folder.getRoot();

        indexStorage.storageRoot(root);
        indexStorage.save();

    }

    @Test
    public void testLoadIndexStorage() throws IOException {
        InputStream inputStream = TestStorage.class.getResourceAsStream("/blankIndex.json");
        File root = folder.getRoot();

        File index = new File(root, IndexStorage.INDEX_FILE);
        Files.copy(inputStream, index.toPath());

        IndexStorage indexStorage = new IndexStorage();
        indexStorage.storageRoot(root);
        indexStorage.load();
    }

}
