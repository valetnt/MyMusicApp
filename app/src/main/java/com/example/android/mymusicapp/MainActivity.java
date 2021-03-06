package com.example.android.mymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
    static final String SECTION = "com.example.android.mymusicapp.SECTION";

    static final LibraryItemsData[] libraryItems = {
            new LibraryItemsData("Tears Dry On Their Own", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("Love Is A Losing Game", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("Rehab", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("You Know I'm No Good", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("Me & Mr Jones", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("Back To Black", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("Just Friends", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("Wake Up Alone", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("Some Unholy War", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("He Can Only Hold Her", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("Addicted", "Amy Winehouse",
                    "Back To Black", R.drawable.amy_winehouse_back_to_black),
            new LibraryItemsData("Chloe In The Afternoon", "St. Vincent", "Strange Mercy", -1),
            new LibraryItemsData("Cruel", "St. Vincent", "Strange Mercy", -1),
            new LibraryItemsData("Cheerleader", "St. Vincent", "Strange Mercy", -1),
            new LibraryItemsData("Surgeon", "St. Vincent", "Strange Mercy", -1),
            new LibraryItemsData("Northern Lights", "St. Vincent", "Strange Mercy", -1),
            new LibraryItemsData("Strange Mercy", "St. Vincent", "Strange Mercy", -1),
            new LibraryItemsData("Neutered Fruit", "St. Vincent", "Strange Mercy", -1),
            new LibraryItemsData("Champagne Year", "St. Vincent", "Strange Mercy", -1),
            new LibraryItemsData("Dilettante", "St. Vincent", "Strange Mercy", -1),
            new LibraryItemsData("Hysterical Strength", "St. Vincent", "Strange Mercy", -1),
            new LibraryItemsData("Year Of The Tiger", "St. Vincent", "Strange Mercy", -1),
            new LibraryItemsData("Five Years", "David Bowie", "The Rise And Fall Of Ziggy Stardust And " +
                    "The Spiders From Mars", R.drawable.david_bowie_ziggy_stardust),
            new LibraryItemsData("Soul Love", "David Bowie", "The Rise And Fall Of Ziggy Stardust And " +
                    "The Spiders From Mars", R.drawable.david_bowie_ziggy_stardust),
            new LibraryItemsData("Moonage Daydream", "David Bowie", "The Rise And Fall Of Ziggy Stardust And " +
                    "The Spiders From Mars", R.drawable.david_bowie_ziggy_stardust),
            new LibraryItemsData("Starman", "David Bowie", "The Rise And Fall Of Ziggy Stardust And " +
                    "The Spiders From Mars", R.drawable.david_bowie_ziggy_stardust),
            new LibraryItemsData("It Ain't Easy", "David Bowie", "The Rise And Fall Of Ziggy Stardust And " +
                    "The Spiders From Mars", R.drawable.david_bowie_ziggy_stardust),
            new LibraryItemsData("Lady Stardust", "David Bowie", "The Rise And Fall Of Ziggy Stardust And " +
                    "The Spiders From Mars", R.drawable.david_bowie_ziggy_stardust),
            new LibraryItemsData("Star", "David Bowie", "The Rise And Fall Of Ziggy Stardust And " +
                    "The Spiders From Mars", R.drawable.david_bowie_ziggy_stardust),
            new LibraryItemsData("Hang On To Yourself", "David Bowie", "The Rise And Fall Of Ziggy Stardust And " +
                    "The Spiders From Mars", R.drawable.david_bowie_ziggy_stardust),
            new LibraryItemsData("Ziggy Stardust", "David Bowie", "The Rise And Fall Of Ziggy Stardust And " +
                    "The Spiders From Mars", R.drawable.david_bowie_ziggy_stardust),
            new LibraryItemsData("Suffragette City", "David Bowie", "The Rise And Fall Of Ziggy Stardust And " +
                    "The Spiders From Mars", R.drawable.david_bowie_ziggy_stardust),
            new LibraryItemsData("Rock 'N' Roll Suicide", "David Bowie", "The Rise And Fall Of Ziggy Stardust And " +
                    "The Spiders From Mars", R.drawable.david_bowie_ziggy_stardust),
            new LibraryItemsData("Beauty And The Beast", "David Bowie", "Heroes", R.drawable.david_bowie_heroes),
            new LibraryItemsData("Jon The Lion", "David Bowie", "Heroes", R.drawable.david_bowie_heroes),
            new LibraryItemsData("Heroes", "David Bowie", "Heroes", R.drawable.david_bowie_heroes),
            new LibraryItemsData("Sons Of The Silent Age", "David Bowie", "Heroes", R.drawable.david_bowie_heroes),
            new LibraryItemsData("Black Out", "David Bowie", "Heroes", R.drawable.david_bowie_heroes),
            new LibraryItemsData("V-2 Schneider", "David Bowie", "Heroes", R.drawable.david_bowie_heroes),
            new LibraryItemsData("Sense Of Doubt", "David Bowie", "Heroes", R.drawable.david_bowie_heroes),
            new LibraryItemsData("Moss Garden", "David Bowie", "Heroes", R.drawable.david_bowie_heroes),
            new LibraryItemsData("Neukoln", "David Bowie", "Heroes", R.drawable.david_bowie_heroes),
            new LibraryItemsData("The Secret Life Of Arabia", "David Bowie", "Heroes", R.drawable.david_bowie_heroes)
    };


    static final SmallItemsData newMusicItems[] = {
            new SmallItemsData("Lust For Life", "Lana Del Rey", R.drawable.lana_del_rey_lust_for_life),
            new SmallItemsData("Thunder", "Imagine Dragons", R.drawable.imagine_dragons_thunder),
            new SmallItemsData("Humanz", "Gorillaz", R.drawable.gorillaz_humanz),
            new SmallItemsData("Automaton", "Jamiroquai", R.drawable.jamiroquai_automaton),
            new SmallItemsData("Divide", "Ed Sheeran", R.drawable.ed_sheeran_divide)
    };

    static final SmallItemsData suggestedItems[] = {
            new SmallItemsData("Shields", "Grizzly Bear", R.drawable.grizzly_bear_shields),
            new SmallItemsData("Born To Die", "Lana Del Rey", R.drawable.lana_del_rey_born_to_die),
            new SmallItemsData("Aladdin Sane", "David Bowie", R.drawable.david_bowie_aladdin_sane),
            new SmallItemsData("Veckatimest", "Grizzly Bear", R.drawable.grizzly_bear_veckatimest),
            new SmallItemsData("Come Away With Me", "Norah Jones", R.drawable.norah_jones_come_away_with_me),
            new SmallItemsData("St. Vincent", "St. Vincent", R.drawable.st_vincent_self_titled)
    };

    static final SmallItemsData mostPopularItems[] = {
            new SmallItemsData("25", "Adele", R.drawable.adele_25),
            new SmallItemsData("Divide", "Ed Sheeran", R.drawable.ed_sheeran_divide),
            new SmallItemsData("Born To Die", "Lana Del Rey", R.drawable.lana_del_rey_born_to_die),
            new SmallItemsData("Views", "Drake", R.drawable.drake_views),
            new SmallItemsData("Dangerous Woman", "Ariana Grande", R.drawable.ariana_grande_dangerous_woman)
    };

    static final SmallItemsData recentlyAddedItems[] = {
            new SmallItemsData("Back To Black", "Amy Winehouse", R.drawable.amy_winehouse_back_to_black),
            new SmallItemsData("The Rise And Fall Of Ziggy Stardust And The Spiders From Mars",
                    "David Bowie", R.drawable.david_bowie_ziggy_stardust),
            new SmallItemsData("Heroes", "David Bowie", R.drawable.david_bowie_heroes)
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

        RecyclerView recyclerView_newMusicItems = (RecyclerView) findViewById(R.id.new_music);
        recyclerView_newMusicItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_newMusicItems.setHasFixedSize(true);
        recyclerView_newMusicItems.setAdapter(new SmallItemsAdapter(newMusicItems, false));
        recyclerView_newMusicItems.setItemAnimator(new DefaultItemAnimator());

        RecyclerView recyclerView_suggestedItems = (RecyclerView) findViewById(R.id.suggested4u);
        recyclerView_suggestedItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_suggestedItems.setHasFixedSize(true);
        recyclerView_suggestedItems.setAdapter(new SmallItemsAdapter(suggestedItems, false));
        recyclerView_suggestedItems.setItemAnimator(new DefaultItemAnimator());

        RecyclerView recyclerView_mostPopularItems = (RecyclerView) findViewById(R.id.most_popular);
        recyclerView_mostPopularItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_mostPopularItems.setHasFixedSize(true);
        recyclerView_mostPopularItems.setAdapter(new SmallItemsAdapter(mostPopularItems, false));
        recyclerView_mostPopularItems.setItemAnimator(new DefaultItemAnimator());

        RecyclerView recyclerView_recentlyAddedItems = (RecyclerView) findViewById(R.id.recently_added);
        recyclerView_recentlyAddedItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_recentlyAddedItems.setHasFixedSize(true);
        recyclerView_recentlyAddedItems.setAdapter(new SmallItemsAdapter(recentlyAddedItems, true));
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

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_signout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
