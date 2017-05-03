package com.example.android.mymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUM;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUMLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ARTIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONGLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_WHOSCALLING;


public class AlbumFromArtist extends AppCompatActivity {

    private String selectedAlbumTitle;
    private ArrayList<String> selectedAlbumSongs;
    private String artist;
    private ArrayList<String> albumListForArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_from_artist);

        selectedAlbumTitle = getIntent().getStringExtra(EXTRA_ALBUM);
        selectedAlbumSongs = getIntent().getStringArrayListExtra(EXTRA_SONGLIST);
        artist = getIntent().getStringExtra(EXTRA_ARTIST);
        albumListForArtist = getIntent().getStringArrayListExtra(EXTRA_ALBUMLIST);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_album_from_artist);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView_albumSongs = (RecyclerView) findViewById(R.id.album_from_artist);
        recyclerView_albumSongs.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView_albumSongs.setHasFixedSize(true);
        recyclerView_albumSongs.addItemDecoration(new LibraryItemsDividerDecoration(this));
        recyclerView_albumSongs.setItemAnimator(new DefaultItemAnimator());
        recyclerView_albumSongs.setAdapter(new LibraryItemsSongsAdapter(selectedAlbumSongs,
                albumListForArtist, selectedAlbumTitle, artist, this, "artistalbum"));

        (findViewById(R.id.back_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToArtist = new Intent(v.getContext(), Artist.class);
                backToArtist.putExtra(EXTRA_ARTIST, artist);
                backToArtist.putExtra(EXTRA_ALBUMLIST,albumListForArtist);
                backToArtist.putExtra(EXTRA_WHOSCALLING,"artistalbum");
                startActivity(backToArtist);
            }
        });

        ((TextView)findViewById(R.id.info_bar_album_from_artist_title)).setText(selectedAlbumTitle);
        ((TextView)findViewById(R.id.info_bar_album_from_artist_artist)).setText(artist);
    }

}
