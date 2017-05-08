package com.example.android.mymusicapp;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUM;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ARTIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONGLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_WHOSCALLING;

public class Album extends AppCompatActivity {

    private String selectedAlbumTitle;
    private ArrayList<String> selectedAlbumSongs;
    private String artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        /*
           The activity "Album" can be either called from the activity "MyLibrary" or re-called
           from the activity "PlayAlbumTrack" (via "Back to Album" button).
        */
        if (getIntent().getStringExtra(EXTRA_WHOSCALLING).equals("library")) {
            selectedAlbumTitle = getIntent().getStringExtra(EXTRA_ALBUM);
            selectedAlbumSongs = getIntent().getStringArrayListExtra(EXTRA_SONGLIST);
            artist = getIntent().getStringExtra(EXTRA_ARTIST);

        } else if (getIntent().getStringExtra(EXTRA_WHOSCALLING).equals("playalbumtrack")) {
            selectedAlbumTitle = getIntent().getStringExtra(EXTRA_ALBUM);
            selectedAlbumSongs = getIntent().getStringArrayListExtra(EXTRA_SONGLIST);
            artist = getIntent().getStringExtra(EXTRA_ARTIST);

        } else {
            Toast.makeText(this, "ERROR: No Intent Received", Toast.LENGTH_SHORT).show();
            finish();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView_albumSongs = (RecyclerView) findViewById(R.id.recycler);
        recyclerView_albumSongs.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView_albumSongs.setHasFixedSize(true);
        recyclerView_albumSongs.addItemDecoration(new LibraryItemsDividerDecoration(this));
        recyclerView_albumSongs.setItemAnimator(new DefaultItemAnimator());
        recyclerView_albumSongs.setAdapter(new LibraryItemsSongsAdapter(selectedAlbumSongs, null,
                selectedAlbumTitle, artist, "album"));

        ((TextView) findViewById(R.id.info_bar_title)).setText(selectedAlbumTitle);
        ((TextView) findViewById(R.id.info_bar_artist)).setText(artist);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_library, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_mylibrary1) {
            return true;
        } else if (id == R.id.action_mylibrary2) {
            return true;
        } else if (id == R.id.action_mylibrary3) {
            return true;
        } else if (id == R.id.action_mylibrary4) {
            return true;
        } else if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}

