package com.example.android.mymusicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUM;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ARTIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONGLIST;

public class Album extends AppCompatActivity {

    private String selectedAlbumTitle;
    private ArrayList<String> selectedAlbumSongs;
    private String artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        selectedAlbumTitle = getIntent().getStringExtra(EXTRA_ALBUM);
        selectedAlbumSongs = getIntent().getStringArrayListExtra(EXTRA_SONGLIST);
        artist = getIntent().getStringExtra(EXTRA_ARTIST);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_album);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView_albumSongs = (RecyclerView) findViewById(R.id.album);
        recyclerView_albumSongs.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView_albumSongs.setHasFixedSize(true);
        recyclerView_albumSongs.addItemDecoration(new LibraryItemsDividerDecoration(this));
        recyclerView_albumSongs.setItemAnimator(new DefaultItemAnimator());
        recyclerView_albumSongs.setAdapter(new LibraryItemsSongsAdapter(selectedAlbumSongs, null,
                selectedAlbumTitle, artist, this, "album"));

        ((TextView)findViewById(R.id.info_bar_album_title)).setText(selectedAlbumTitle);
        ((TextView)findViewById(R.id.info_bar_album_artist)).setText(artist);

    }
}

