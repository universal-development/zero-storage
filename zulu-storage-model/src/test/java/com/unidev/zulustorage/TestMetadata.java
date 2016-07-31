package com.unidev.zulustorage;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Test cases for metadata loading
 */
public class TestMetadata {

    @Test
    public void testMetadataCreation() {
        Metadata metadata = Metadata.newMetadata();

        assertThat(metadata, is(not(nullValue())));
        assertThat(metadata.isEmpty(), is(true));

        Metadata potatoMetadata = Metadata.newMetadata("potato");
        assertThat(potatoMetadata._id(), is("potato"));
    }

    @Test
    public void testMetadataRecordFetching() {
        Metadata metadata = Metadata.newMetadata("potato");
        metadata.put("kappa", "kappa123");
        metadata.link("tomato-link");

        assertThat(metadata.opt("kappa"), is("kappa123"));
        assertThat(metadata.opt("tomato"), is(nullValue()));
        assertThat(metadata.opt("tomat", "defaultTomato"), is("defaultTomato"));

        assertThat(metadata.get("kappa"), is("kappa123"));
        assertThat(metadata.get("kappa123"), is(nullValue()));

        assertThat(metadata.link(), is("tomato-link"));

    }

}

