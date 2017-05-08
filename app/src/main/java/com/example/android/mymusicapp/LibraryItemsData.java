package com.example.android.mymusicapp;

public class LibraryItemsData {

    private String songTitle;
    private String artistName;
    private String albumTitle;
    private int imageID;

    public LibraryItemsData(String song, String artist, String album, int id) {
        artistName = artist;
        songTitle = song;
        albumTitle = album;
        imageID = id;
    }

    // getters:

    public String getSongTitle() {
        return songTitle;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getImageID() {
        return imageID;
    }

    /*
       Method getImageForArtist(String name) checks if there is a match between
       the field "artistName" and the string "name". If there is a match, it returns the
       field "imageID" (of type int). Else it returns 0.
    */
    public int getImageForArtist(String name) {
        if (name.equals(artistName)) {
            return imageID;
        } else {
            return 0;
        }
    }

    /*
       Method getImageForAlbum(String title) checks if there is a match between
       the field "albumTitle" and the string "title". If there is a match, it returns the
       field "imageID" (of type int). Else it returns 0.
    */
    public int getImageForAlbum(String title) {
        if (title.equals(albumTitle)) {
            return imageID;
        } else {
            return 0;
        }
    }

    /*
       Method getImageForSong(String title) checks if there is a match between
       the field "songTitle" and the string "title". If there is a match, it returns the
       field "imageID" (of type int). Else it returns 0.
    */
    public int getImageForSong(String title) {
        if (title.equals(songTitle)) {
            return imageID;
        } else {
            return 0;
        }
    }
}
