package com.example.android.mymusicapp;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUM;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ARTIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_COVER;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONG;


public class PlaySingle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_single);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String selectedAlbumTitle;
        String artist;
        String songTitle;
        int albumCoverID;

        selectedAlbumTitle = getIntent().getStringExtra(EXTRA_ALBUM);
        artist = getIntent().getStringExtra(EXTRA_ARTIST);
        songTitle = getIntent().getStringExtra(EXTRA_SONG);
        albumCoverID = getIntent().getIntExtra(EXTRA_COVER, R.drawable.music_note);

        ((TextView) findViewById(R.id.now_playing_artist)).setText(artist);
        ((TextView) findViewById(R.id.now_playing_song)).setText(songTitle);
        ((TextView) findViewById(R.id.now_playing_album)).setText(selectedAlbumTitle);
        ((ImageView) findViewById(R.id.album_cover)).setImageResource(albumCoverID);

        // The extra controls frame opens/close by touching the album cover
        final View toggle = findViewById(R.id.media_buttons_extra);
        toggle.setVisibility(View.INVISIBLE);
        findViewById(R.id.album_cover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (toggle.getVisibility() == View.VISIBLE) {
                    toggle.setVisibility(View.INVISIBLE);
                } else {
                    toggle.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_now_playing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_now_playing1) {
            return true;
        } else if (id == R.id.action_now_playing2) {
            return true;
        } else if (id == R.id.action_now_playing3) {
            return true;
        } else if (id == R.id.action_now_playing4) {
            return true;
        } else if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
