package com.example.andre.mytestapp;

import com.example.andre.mytestapp.model.Artist;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.text.Normalizer;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FormatTextUnitTest {

    @Test
    public void terminatedSong() {

        assertEquals(FormatText.getCountTextSongs(5), "5 песен");
        assertEquals(FormatText.getCountTextSongs(1), "1 песня");
        assertEquals(FormatText.getCountTextSongs(0), "0 песен");
        assertEquals(FormatText.getCountTextSongs(13), "13 песен");
        assertEquals(FormatText.getCountTextSongs(1023), "1023 песни");
        assertEquals(FormatText.getCountTextSongs(10), "10 песен");
        assertNotEquals(FormatText.getCountTextSongs(10), "11 песня");

    }
    @Test
    public void terminatedAlbums() {
        assertEquals(FormatText.getCountTextAlbums(1), "1 альбом");
        assertEquals(FormatText.getCountTextAlbums(0), "0 альбомов");
        assertEquals(FormatText.getCountTextAlbums(14), "14 альбомов");
        assertEquals(FormatText.getCountTextAlbums(19), "19 альбомов");
        assertEquals(FormatText.getCountTextAlbums(2), "2 альбома");
        assertNotEquals(FormatText.getCountTextAlbums(10), "10 альбома");
    }
    @Test
    public void commaSpace() {
        StringBuilder str = new StringBuilder();

        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i<10;i++) {
            list.add("A"+i);
            if ( i == 9 ) str.append("A").append(i); else str.append("A").append(i).append(", ");
        }

        assertEquals(FormatText.changeCommaSymbol(list), str.toString());
        assertNotEquals(FormatText.changeCommaSymbol(list), str.toString()+",");
    }

}