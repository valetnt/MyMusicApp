package com.example.android.mymusicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import java.util.ArrayList;

import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUMLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ARTIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_WHOSCALLING;

public class Artist extends AppCompatActivity {

    private String selectedArtistName;
    private ArrayList<String> selectedArtistAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        if( getIntent().getStringExtra(EXTRA_WHOSCALLING).equals("library") ) {
            selectedArtistName = getIntent().getStringExtra(EXTRA_ARTIST);
            selectedArtistAlbums = getIntent().getStringArrayListExtra(EXTRA_ALBUMLIST);
        } else if ( getIntent().getStringExtra(EXTRA_WHOSCALLING).equals("artistalbum") ) {
            selectedArtistName = getIntent().getStringExtra(EXTRA_ARTIST);
            selectedArtistAlbums = getIntent().getStringArrayListExtra(EXTRA_ALBUMLIST);
        } else {
            Toast.makeText(this, "ERROR: No Intent Received", Toast.LENGTH_SHORT).show();
            finish();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.artist_toolbar);
        toolbar.setTitle(selectedArtistName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView_artistAlbums = (RecyclerView) findViewById(R.id.artist);
        recyclerView_artistAlbums.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView_artistAlbums.setHasFixedSize(true);
        recyclerView_artistAlbums.addItemDecoration(new LibraryItemsDividerDecoration(this));
        recyclerView_artistAlbums.setItemAnimator(new DefaultItemAnimator());
        recyclerView_artistAlbums.setAdapter(new LibraryItemsAlbumsAdapter(selectedArtistAlbums, this, "artistalbum"));
    }
}
