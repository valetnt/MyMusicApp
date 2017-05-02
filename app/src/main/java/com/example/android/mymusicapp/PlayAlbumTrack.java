package com.example.android.mymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUM;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUMLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ARTIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONGLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_WHOSCALLING;

public class PlayAlbumTrack extends AppCompatActivity {

    private String selectedAlbumTitle;
    private ArrayList<String> selectedAlbumSongs;
    private String artist;
    private ArrayList<String> albumListForArtist;
    private String senderActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_album_track);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        selectedAlbumTitle = getIntent().getStringExtra(EXTRA_ALBUM);
        selectedAlbumSongs = getIntent().getStringArrayListExtra(EXTRA_SONGLIST);
        artist = getIntent().getStringExtra(EXTRA_ARTIST);
        albumListForArtist = getIntent().getStringArrayListExtra(EXTRA_ALBUMLIST);
        senderActivity = getIntent().getStringExtra(EXTRA_WHOSCALLING);

        findViewById(R.id.back_to_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (senderActivity.equals("album")) {
                    Intent goBackToAlbum = new Intent(v.getContext(), Album.class);
                    goBackToAlbum.putExtra(EXTRA_ALBUM, selectedAlbumTitle);
                    goBackToAlbum.putExtra(EXTRA_SONGLIST, selectedAlbumSongs);
                    goBackToAlbum.putExtra(EXTRA_ARTIST, artist);
                    startActivity(goBackToAlbum);

                } else if (senderActivity.equals("artistalbum")) {
                    Intent goBackToAlbum = new Intent(v.getContext(), Album.class);
                    goBackToAlbum.putExtra(EXTRA_ALBUM, selectedAlbumTitle);
                    goBackToAlbum.putExtra(EXTRA_SONGLIST, selectedAlbumSongs);
                    goBackToAlbum.putExtra(EXTRA_ARTIST, artist);
                    goBackToAlbum.putExtra(EXTRA_ALBUMLIST,albumListForArtist);
                    startActivity(goBackToAlbum);
                }

            }
        });

    }

}
