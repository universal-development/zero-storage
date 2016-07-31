package com.unidev.zulustorage;

import com.unidev.zulustorage.index.IndexStorage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static com.unidev.zulustorage.Metadata.*;

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
    public void testLoadEmptyIndexStorage() throws IOException {
        InputStream inputStream = TestStorage.class.getResourceAsStream("/blankIndex.json");
        File root = folder.getRoot();

        File index = new File(root, IndexStorage.INDEX_FILE);
        Files.copy(inputStream, index.toPath());

        IndexStorage indexStorage = new IndexStorage();
        indexStorage.storageRoot(root);
        indexStorage.load();
    }

    @Test
    public void testSaveLoadIndexStorage() {

        IndexStorage indexStorage = new IndexStorage();

        Storage index = new Storage();
        index.details().put("Potato", "Tomato");
        index.details().put("Tomato", null);
        indexStorage.index(index);

        File root = folder.getRoot();

        indexStorage.storageRoot(root);
        indexStorage.save();

        IndexStorage storageToLoad = new IndexStorage();
        storageToLoad.storageRoot(root);
        storageToLoad.load();

        Storage loadedIndex = storageToLoad.index();
        assertThat(loadedIndex, is(not(nullValue())));
        assertThat(loadedIndex.details(), is(not(nullValue())));

        assertThat(loadedIndex.details().opt("Potato"), is("Tomato"));
    }

    @Test
    public void testIndexMetadataManagement() throws IOException {
        IndexStorage indexStorage = new IndexStorage();
//        File root = new File("/tmp/111");
//        root.mkdirs();
        File root = folder.getRoot();
        indexStorage.storageRoot(root);
        indexStorage.index().details().put(IndexStorage.META_PER_STORAGE_KEY, 10);

        for(int i = 0;i<13;i++) {
            Metadata meta = newMetadata();
            meta._id("id_" + i);
            indexStorage.addMetadata(meta);
        }

        IndexStorage loadStorage = new IndexStorage().storageRoot(root).load();
        assertThat(loadStorage.storageFiles(), is(not(nullValue())));
        assertThat(loadStorage.storageFiles().size(), is(2)); // 13 records should be stored in 2 files: 10 + 3

        String id = loadStorage.storageFiles().get(1)._id();
        Optional<Storage> storage = loadStorage.storage(id);
        assertThat(storage.isPresent(), is(true));

        Storage storageEntity = storage.get();

        assertThat(storageEntity.metadata().size(), is(10));
    }

}
