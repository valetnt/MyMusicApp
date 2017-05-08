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

        /*
           The activity "Artist" can be either called from the activity "MyLibrary" or re-called
           from the activity "AlbumFromArtist" (via "Back to Artist" button).
        */
        if (getIntent().getStringExtra(EXTRA_WHOSCALLING).equals("library")) {
            selectedArtistName = getIntent().getStringExtra(EXTRA_ARTIST);
            selectedArtistAlbums = getIntent().getStringArrayListExtra(EXTRA_ALBUMLIST);

        } else if (getIntent().getStringExtra(EXTRA_WHOSCALLING).equals("artistalbum")) {
            selectedArtistName = getIntent().getStringExtra(EXTRA_ARTIST);
            selectedArtistAlbums = getIntent().getStringArrayListExtra(EXTRA_ALBUMLIST);

        } else {
            Toast.makeText(this, "ERROR: No Intent Received", Toast.LENGTH_SHORT).show();
            finish();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.artist_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView_artistAlbums = (RecyclerView) findViewById(R.id.recycler);
        recyclerView_artistAlbums.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView_artistAlbums.setHasFixedSize(true);
        recyclerView_artistAlbums.addItemDecoration(new LibraryItemsDividerDecoration(this));
        recyclerView_artistAlbums.setItemAnimator(new DefaultItemAnimator());
        recyclerView_artistAlbums.setAdapter(new LibraryItemsAlbumsAdapter(selectedArtistAlbums,
                "artist"));

        ((TextView) findViewById(R.id.info_bar_artist)).setText(selectedArtistName);
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
