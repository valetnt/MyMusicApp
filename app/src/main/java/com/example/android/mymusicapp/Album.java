package com.example.android.mymusicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import static com.example.android.mymusicapp.LibraryItemsAlbumsAdapter.EXTRA_ALBUM;
import static com.example.android.mymusicapp.LibraryItemsAlbumsAdapter.EXTRA_SONGLIST;
import static com.example.android.mymusicapp.LibraryItemsArtistsAdapter.EXTRA_ARTIST;

public class Album extends AppCompatActivity {

    private String selectedAlbumTitle;
    private ArrayList<String> selectedAlbumSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        selectedAlbumTitle = getIntent().getStringExtra(EXTRA_ALBUM);
        selectedAlbumSongs = getIntent().getStringArrayListExtra(EXTRA_SONGLIST);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        String toolbarTitle = getIntent().getStringExtra(EXTRA_ARTIST) + " - " + selectedAlbumTitle;
        toolbar.setTitle(toolbarTitle);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView_albumSongs = (RecyclerView) findViewById(R.id.album);
        recyclerView_albumSongs.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView_albumSongs.setHasFixedSize(true);
        recyclerView_albumSongs.addItemDecoration(new LibraryItemsDividerDecoration(this));
        recyclerView_albumSongs.setItemAnimator(new DefaultItemAnimator());
        recyclerView_albumSongs.setAdapter(new LibraryItemsSongsAdapter(selectedAlbumSongs));

    }
}

