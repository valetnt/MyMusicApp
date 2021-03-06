package com.example.android.mymusicapp;

public class SmallItemsData {

    public String title;
    public String artist;
    public int imageID;

    public SmallItemsData(String title, String artist, int imageID) {
        this.title = title;
        this.artist = artist;
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getImageID() {
        return imageID;
    }
}
