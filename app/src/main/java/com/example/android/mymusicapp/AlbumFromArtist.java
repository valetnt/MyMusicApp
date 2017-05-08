package com.example.android.mymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

        /*
           The activity "AlbumFromArtist" can be either called from the activity "Artist" or
           re-called from the activity "PlayAlbumTrack" (via "Back to Album" button).
        */
        if (getIntent().getStringExtra(EXTRA_WHOSCALLING).equals("artist")) {
            selectedAlbumTitle = getIntent().getStringExtra(EXTRA_ALBUM);
            selectedAlbumSongs = getIntent().getStringArrayListExtra(EXTRA_SONGLIST);
            artist = getIntent().getStringExtra(EXTRA_ARTIST);
            albumListForArtist = getIntent().getStringArrayListExtra(EXTRA_ALBUMLIST);

        } else if (getIntent().getStringExtra(EXTRA_WHOSCALLING).equals("playalbumtrack")) {
            selectedAlbumTitle = getIntent().getStringExtra(EXTRA_ALBUM);
            selectedAlbumSongs = getIntent().getStringArrayListExtra(EXTRA_SONGLIST);
            artist = getIntent().getStringExtra(EXTRA_ARTIST);
            albumListForArtist = getIntent().getStringArrayListExtra(EXTRA_ALBUMLIST);

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
        recyclerView_albumSongs.setAdapter(new LibraryItemsSongsAdapter(selectedAlbumSongs,
                albumListForArtist, selectedAlbumTitle, artist, "artistalbum"));

        // Set an "OnClickListener" on the button "Back to Artist"
        (findViewById(R.id.back_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToArtist = new Intent(v.getContext(), Artist.class);
                backToArtist.putExtra(EXTRA_ARTIST, artist);
                backToArtist.putExtra(EXTRA_ALBUMLIST, albumListForArtist);
                backToArtist.putExtra(EXTRA_WHOSCALLING, "artistalbum");
                startActivity(backToArtist);
            }
        });

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
