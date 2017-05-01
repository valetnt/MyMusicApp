package com.example.android.mymusicapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import static com.example.android.mymusicapp.LibraryItemsArtistsAdapter.EXTRA_ALBUMLIST;
import static com.example.android.mymusicapp.LibraryItemsArtistsAdapter.EXTRA_ARTIST;

public class Artist extends AppCompatActivity {

    private String selectedArtistName;
    private ArrayList<String> selectedArtistAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        selectedArtistName = getIntent().getStringExtra(EXTRA_ARTIST);
        selectedArtistAlbums = getIntent().getStringArrayListExtra(EXTRA_ALBUMLIST);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(selectedArtistName);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView_artistAlbums = (RecyclerView) findViewById(R.id.artist);
        recyclerView_artistAlbums.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView_artistAlbums.setHasFixedSize(true);
        recyclerView_artistAlbums.addItemDecoration(new LibraryItemsDividerDecoration(this));
        recyclerView_artistAlbums.setItemAnimator(new DefaultItemAnimator());
        recyclerView_artistAlbums.setAdapter(new LibraryItemsAlbumsAdapter(selectedArtistAlbums, this));

    }

}
