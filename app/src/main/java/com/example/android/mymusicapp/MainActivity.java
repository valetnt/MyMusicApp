package com.example.android.mymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final String EXTRA_ARTIST = "com.example.android.mymusicapp.EXTRA_ARTIST";
    static final String EXTRA_ALBUM = "com.example.android.mymusicapp.EXTRA_ALBUM";
    static final String EXTRA_SONGLIST = "com.example.android.mymusicapp.EXTRA_SONGLIST";
    static final String EXTRA_ALBUMLIST = "com.example.android.mymusicapp.EXTRA_ALBUMLIST";
    static final String EXTRA_WHOSCALLING = "com.example.android.mymusicapp.EXTRA_WHOSCALLING";
    static final String EXTRA_SONG = "com.example.android.mymusicapp.EXTRA_SONG";
    static final String EXTRA_COVER = "com.example.android.mymusicapp.EXTRA_COVER";

    static final LibraryItemsData[] libraryItems = {
            new LibraryItemsData("Tears Dry On Their Own", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("Love Is A Losing Game", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("Sleeping Ute", "Grizzly Bear",
                    "Shields", R.drawable.grizzly_bear_shields),
            new LibraryItemsData("All My Stars Aligned", "St. Vincent", "Marry Me", -1),
            new LibraryItemsData("The Neighbors", "St. Vincent", "Actor", -1),
            new LibraryItemsData("Save Me From What I Want", "St. Vincent", "Actor", -1)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_main1) {
            return true;
        } else if (id == R.id.action_main2) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_library) {
            Intent openMyLibrary = new Intent(this, MyLibrary.class);
            startActivity(openMyLibrary);

        } else if (id == R.id.nav_playlists) {

        } else if (id == R.id.nav_store) {
            Intent openMyStore = new Intent(this, MyStore.class);
            startActivity(openMyStore);

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_signout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
