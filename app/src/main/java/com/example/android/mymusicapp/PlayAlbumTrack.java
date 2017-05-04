package com.example.android.mymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUM;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUMLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ARTIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_COVER;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONG;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONGLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_WHOSCALLING;

public class PlayAlbumTrack extends AppCompatActivity {

    private String selectedAlbumTitle;
    private ArrayList<String> selectedAlbumSongs;
    private String artist;
    private ArrayList<String> albumListForArtist;
    private String songTitle;
    private int albumCoverID;
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
        songTitle = getIntent().getStringExtra(EXTRA_SONG);
        albumCoverID = getIntent().getIntExtra(EXTRA_COVER,R.drawable.music_note);
        senderActivity = getIntent().getStringExtra(EXTRA_WHOSCALLING);

        findViewById(R.id.back_to_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (senderActivity.equals("album")) {
                    Intent goBackToAlbum = new Intent(v.getContext(), Album.class);
                    goBackToAlbum.putExtra(EXTRA_ALBUM, selectedAlbumTitle);
                    goBackToAlbum.putExtra(EXTRA_SONGLIST, selectedAlbumSongs);
                    goBackToAlbum.putExtra(EXTRA_ARTIST, artist);
                    goBackToAlbum.putExtra(EXTRA_WHOSCALLING,"playalbumtrack");
                    startActivity(goBackToAlbum);

                } else if (senderActivity.equals("artistalbum")) {
                    Intent goBackToAlbumFromArtist = new Intent(v.getContext(), AlbumFromArtist.class);
                    goBackToAlbumFromArtist.putExtra(EXTRA_ALBUM, selectedAlbumTitle);
                    goBackToAlbumFromArtist.putExtra(EXTRA_SONGLIST, selectedAlbumSongs);
                    goBackToAlbumFromArtist.putExtra(EXTRA_ARTIST, artist);
                    goBackToAlbumFromArtist.putExtra(EXTRA_ALBUMLIST,albumListForArtist);
                    goBackToAlbumFromArtist.putExtra(EXTRA_WHOSCALLING,"playalbumtrack");
                    startActivity(goBackToAlbumFromArtist);
                }

            }
        });


        ((TextView) findViewById(R.id.now_playing_artist)).setText(artist);
        ((TextView) findViewById(R.id.now_playing_song)).setText(songTitle);
        ((TextView) findViewById(R.id.now_playing_album)).setText(selectedAlbumTitle);
        ((ImageView) findViewById(R.id.album_cover)).setImageResource(albumCoverID);

        final View toggle = findViewById(R.id.media_buttons_extra);
        toggle.setVisibility(View.INVISIBLE);
        findViewById(R.id.album_cover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(toggle.getVisibility()==View.VISIBLE){
                    toggle.setVisibility(View.INVISIBLE);
                } else {
                    toggle.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
