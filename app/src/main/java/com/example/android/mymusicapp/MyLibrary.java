package com.example.android.mymusicapp;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static android.R.attr.fragment;
import static com.example.android.mymusicapp.MainActivity.libraryItems;
import static com.example.android.mymusicapp.MyLibrary.PlaceholderFragment.ARG_SECTION_NUMBER;


public class MyLibrary extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_library);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        /*if(savedInstanceState==null){
            PlaceholderFragment.newInstance(1);
        }*/

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
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {

                case 1:

                    View rootView1 = inflater.inflate(R.layout.fragment_my_library, container, false);

                    ArrayList<String> ArtistsList = new ArrayList<>();
                    for (int i = 0; i < libraryItems.length; i++) {
                        ArtistsList.add(libraryItems[i].getArtistName());
                    }

                    Set<String> hsArtistsList = new HashSet<>();
                    hsArtistsList.addAll(ArtistsList);
                    ArtistsList.clear();
                    ArtistsList.addAll(hsArtistsList);

                    Collections.sort(ArtistsList, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o1.compareToIgnoreCase(o2);
                        }
                    });

                    RecyclerView recyclerView_artists = (RecyclerView) rootView1.findViewById(R.id.mylibrary_list_item);
                    recyclerView_artists.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false));
                    recyclerView_artists.setAdapter(new LibraryItemsArtistsAdapter(ArtistsList, getContext()));
                    recyclerView_artists.setHasFixedSize(true);
                    recyclerView_artists.addItemDecoration(new LibraryItemsDividerDecoration(getContext()));
                    recyclerView_artists.setItemAnimator(new DefaultItemAnimator());

                    return rootView1;

                case 2:

                    View rootView2 = inflater.inflate(R.layout.fragment_my_library, container, false);

                    ArrayList<String> AlbumsList = new ArrayList<>();
                    for (int i = 0; i < libraryItems.length; i++) {
                        AlbumsList.add(libraryItems[i].getAlbumTitle());
                    }

                    Set<String> hsAlbumsList = new HashSet<>();
                    hsAlbumsList.addAll(AlbumsList);
                    AlbumsList.clear();
                    AlbumsList.addAll(hsAlbumsList);

                    Collections.sort(AlbumsList, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o1.compareToIgnoreCase(o2);
                        }
                    });

                    RecyclerView recyclerView_albums = (RecyclerView) rootView2.findViewById(R.id.mylibrary_list_item);
                    recyclerView_albums.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false));
                    recyclerView_albums.setAdapter(new LibraryItemsAlbumsAdapter(AlbumsList, getContext(), "library"));
                    recyclerView_albums.setHasFixedSize(true);
                    recyclerView_albums.addItemDecoration(new LibraryItemsDividerDecoration(getContext()));
                    recyclerView_albums.setItemAnimator(new DefaultItemAnimator());

                    return rootView2;

                case 3:

                    View rootView3 = inflater.inflate(R.layout.fragment_my_library, container, false);

                    ArrayList<String> SongsList = new ArrayList<>();
                    for (int i = 0; i < libraryItems.length; i++) {
                        SongsList.add(libraryItems[i].getSongTitle());
                    }

                    Set<String> hsSongsList = new HashSet<>();
                    hsSongsList.addAll(SongsList);
                    SongsList.clear();
                    SongsList.addAll(hsSongsList);

                    Collections.sort(SongsList, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o1.compareToIgnoreCase(o2);
                        }
                    });

                    RecyclerView recyclerView_Songs = (RecyclerView) rootView3.findViewById(R.id.mylibrary_list_item);
                    recyclerView_Songs.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false));
                    recyclerView_Songs.setAdapter(new LibraryItemsSongsAdapter(SongsList, getContext()));
                    recyclerView_Songs.setHasFixedSize(true);
                    recyclerView_Songs.addItemDecoration(new LibraryItemsDividerDecoration(getContext()));
                    recyclerView_Songs.setItemAnimator(new DefaultItemAnimator());

                    return rootView3;

                default: return null;
            }
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.section_label1);
                case 1:
                    return getString(R.string.section_label2);
                case 2:
                    return getString(R.string.section_label3);
            }
            return null;
        }
    }
}
