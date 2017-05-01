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
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        RecyclerView recyclerView_suggestedItems = (RecyclerView) findViewById(R.id.suggested4u);
        SmallItemsData suggestedItems[] = {
                new SmallItemsData("Veckatimest", "Grizzly Bear", R.drawable.grizzly_bear_veckatimest),
                new SmallItemsData("Rumours", "Fleetwood Mac", R.drawable.fleetwood_mac_rumours),
                new SmallItemsData("Aladdin Sane", "David Bowie", R.drawable.david_bowie_aladdin_sane),
                new SmallItemsData("Come Away With Me", "Norah Jones", R.drawable.norah_jones_come_away_with_me)
        };

        RecyclerView recyclerView_mostPopularItems = (RecyclerView) findViewById(R.id.most_popular);
        SmallItemsData mostPopularItems[] = {
                new SmallItemsData("25", "Adele", R.drawable.adele_25),
                new SmallItemsData("Divide", "Ed Sheeran", R.drawable.ed_sheeran_divide),
                new SmallItemsData("St. Vincent", "St. Vincent", R.drawable.st_vincent_self_titled),
                new SmallItemsData("Born To Die", "Lana Del Rey", R.drawable.lana_del_rey_born_to_die)
        };

        RecyclerView recyclerView_recentlyAddedItems = (RecyclerView) findViewById(R.id.recently_added);
        SmallItemsData recentlyAddedItems[] = {
                new SmallItemsData("Back To Black", "Amy Winehouse", R.drawable.amy_winehouse_back_to_black),
                new SmallItemsData("Shields", "Grizzly Bear", R.drawable.grizzly_bear_shields)
        };

        recyclerView_suggestedItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_suggestedItems.setHasFixedSize(true);
        recyclerView_suggestedItems.setAdapter(new SmallItemsAdapter(suggestedItems));
        recyclerView_suggestedItems.setItemAnimator(new DefaultItemAnimator());

        recyclerView_mostPopularItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_mostPopularItems.setHasFixedSize(true);
        recyclerView_mostPopularItems.setAdapter(new SmallItemsAdapter(mostPopularItems));
        recyclerView_mostPopularItems.setItemAnimator(new DefaultItemAnimator());

        recyclerView_recentlyAddedItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_recentlyAddedItems.setHasFixedSize(true);
        recyclerView_recentlyAddedItems.setAdapter(new SmallItemsAdapter(recentlyAddedItems));
        recyclerView_recentlyAddedItems.setItemAnimator(new DefaultItemAnimator());

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
        } else if (id == R.id.action_main3) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_library) {
            Intent openMyLibrary = new Intent(this, MyLibrary.class);
            startActivity(openMyLibrary);

        } else if (id == R.id.nav_playlists) {

        } else if (id == R.id.nav_store) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_signout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
