package com.unidev.zerostorage;

import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


/**
 * Tests for storage operations
 */
public class TestStorage {

    @Test
    public void testStorageSave() throws IOException {
        Storage storage = new Storage();

        Metadata metadata = new Metadata();
        metadata.set_id("Potato");
        metadata.setLink("/resource/potato");
        metadata.put("test", "test1");
        storage.addMetadata(metadata);

        Metadata metadata2 = new Metadata();
        metadata2.put("test3", "test3");
        storage.addMetadata(metadata2);

        File tempFile = File.createTempFile("storage", ".json");
        tempFile.deleteOnExit();

        StorageMapper.storageMapper().saveSource(new FileOutputStream(tempFile)).save(storage);

    }

    @Test
    public void testStorageLoading() throws MalformedURLException, URISyntaxException {
        InputStream inputStream = TestStorage.class.getResourceAsStream("/test.json");
        Storage storage = StorageMapper.storageMapper().loadSource(inputStream).load();

        assertThat(storage, is(not(nullValue())));
        assertThat(storage.getMetadata(), is(not(nullValue())));
        assertThat(storage.getMetadata().size(), is (2));
        assertThat(storage.getMetadata().get(1).opt("testKey2"), is("testValue2"));

    }
}
