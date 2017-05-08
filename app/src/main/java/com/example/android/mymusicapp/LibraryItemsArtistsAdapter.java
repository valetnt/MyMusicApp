package com.example.android.mymusicapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUMLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ARTIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_WHOSCALLING;
import static com.example.android.mymusicapp.MainActivity.libraryItems;

public class LibraryItemsArtistsAdapter
        extends RecyclerView.Adapter<LibraryItemsArtistsAdapter.ViewHolder> {
    
    private ArrayList<String> artistList;

    public LibraryItemsArtistsAdapter(ArrayList<String> artistList) {
        this.artistList = artistList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LibraryItemsArtistsAdapter.ViewHolder onCreateViewHolder (ViewGroup parent,
                                                                     int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.library_list_items, parent, false);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        // Set artist name for the item at the current position
        viewHolder.txtViewArtist.setText(artistList.get(position));

        /*
           Set thumbnail for the item at the current position: find the first library item
           that matches the artist name and set its image as the thumbnail. If method
           getImageForArtist returns -1, it means that there is no image available.
        */
        int i = 0;
        while (i < libraryItems.length) {

            if (libraryItems[i].getImageForArtist(artistList.get(position)) == 0) {
                viewHolder.imgViewIcon.setImageResource(R.drawable.music_note);
                i++;
            } else if (libraryItems[i].getImageForArtist(artistList.get(position)) == -1) {
                viewHolder.imgViewIcon.setImageResource(R.drawable.music_note);
                break;
            }
            else {
                viewHolder.imgViewIcon.setImageResource(libraryItems[i].getImageID());
                break;
            }
        }

        /*
           When one of the items (artists) is clicked, an intent is sent to the activity "Artist".
           We need to attach two extras to the intent:
           EXTRA_ARTIST (the clicked item),
           EXTRA_ALBUMLIST (all the albums by the clicked artist).
         */
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            ArrayList<String> albumList = new ArrayList<>();

            @Override
            public void onClick (View view){

                /*
                   Determine the list of albums for the clicked artist
                   scanning through the library items looking for an artist match.
                   Every time there is a match, call getAlbumTitle() on the library item
                   and add the output string to the ArrayList<String> "albumList".
                */

                for (int j = 0; j < libraryItems.length; j++) {

                    if(libraryItems[j].getArtistName().equals
                            (artistList.get(viewHolder.getAdapterPosition()))) {
                        albumList.add(libraryItems[j].getAlbumTitle());
                    }
                }

                // Avoid repetitions in the album list
                Set<String> hsAlbumList = new HashSet<>();
                hsAlbumList.addAll(albumList);
                albumList.clear();
                albumList.addAll(hsAlbumList);

                // Sort in alphabetical order
                Collections.sort(albumList, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareToIgnoreCase(o2);
                    }
                });

                Intent browseArtistAlbums = new Intent(view.getContext(), Artist.class);
                browseArtistAlbums.putExtra(EXTRA_ARTIST,
                        artistList.get(viewHolder.getAdapterPosition()));
                browseArtistAlbums.putExtra(EXTRA_ALBUMLIST, albumList);
                browseArtistAlbums.putExtra(EXTRA_WHOSCALLING,"library");
                view.getContext().startActivity(browseArtistAlbums);
            }
        });
    }

    // Return the size of your libraryItems (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return artistList.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewArtist;
        private ImageView imgViewIcon;

        private ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            // Artist name
            txtViewArtist = (TextView) itemLayoutView.findViewById(R.id.library_txtView);
            // Artist thumbnail
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.library_thumb);
        }
    }
}

