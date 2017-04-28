package com.example.android.mymusicapp;

/**
 * Adapted from http://hmkcode.com/android-simple-recyclerview-widget-example/
 */

public class ItemData {

    public String title;
    public String artist;
    public int imageID;

    public ItemData(String title, String artist, int imageID){

        this.title = title;
        this.artist = artist;
        this.imageID = imageID;
    }

    public String getTitle () {

        return title;
    }

    public String getArtist () {

        return artist;
    }

    public int getImageID () {

        return imageID;
    }
}
