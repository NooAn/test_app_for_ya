package com.example.andre.mytestapp;

import com.example.andre.mytestapp.model.Artist;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Andre on 22.04.2016.
 */
public class ArtistUnitTest extends TestCase {
    Artist artist;
    ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        list.add("Шняга");
        list.add("Блатняк!\"№;;%%:?*()");
        artist = new Artist(10,"Петруха Мокруха",list, 777, 228,"http://bytarka","Наколи мне купола", null);
    }

    @Override
    protected void tearDown() throws Exception {
        artist = null;
        super.tearDown();
    }
    public void testAlbums() throws Exception {
        assertEquals(228, artist.getAlbums());
    }
    public void testList() {
        assertTrue(list.size()==2);
    }
    public void testTracks() throws Exception {
        assertFalse(100500==artist.getTracks());
    }

    public void testLink() throws Exception {
        artist.setLink("http://example.com");
        assertEquals("http://example.com", artist.getLink());
    }

    public void testDescription() throws Exception {
        artist.setDescription("Пожизненная песня");
        assertEquals("Пожизненная песня", artist.getDescription());
    }

}
