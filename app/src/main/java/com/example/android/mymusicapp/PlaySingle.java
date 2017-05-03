package com.example.android.mymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import static com.example.android.mymusicapp.MainActivity.EXTRA_SECTION;
import static com.example.android.mymusicapp.MainActivity.EXTRA_WHOSCALLING;
import static com.example.android.mymusicapp.MyLibrary.PlaceholderFragment.ARG_SECTION_NUMBER;

public class PlaySingle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_single);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
