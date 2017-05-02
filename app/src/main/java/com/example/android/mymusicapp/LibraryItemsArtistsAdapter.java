package com.example.android.mymusicapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    
    private ArrayList<String> ArtistsList;
    private Context context;

    public LibraryItemsArtistsAdapter(ArrayList<String> ArtistsList, Context context) {
        this.ArtistsList = ArtistsList;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LibraryItemsArtistsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.library_list_items, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        viewHolder.txtViewArtist.setText(ArtistsList.get(position));

        int i = 0;

        while (i < libraryItems.length) {

            if (libraryItems[i].getImageForArtist(ArtistsList.get(position)) == 0) {
                viewHolder.imgViewIcon.setImageResource(R.mipmap.ic_music_note_black_24dp);
                i++;
            } else if (libraryItems[i].getImageForArtist(ArtistsList.get(position)) == -1) {
                viewHolder.imgViewIcon.setImageResource(R.mipmap.ic_music_note_black_24dp);
                break;
            }
            else {
                viewHolder.imgViewIcon.setImageResource(libraryItems[i].getImageID());
                break;
            }
        }

        final ArrayList<String> albumList = new ArrayList<>();
        final int numberOfItems = libraryItems.length;

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view){
                for (int j = 0; j < numberOfItems; j++) {

                    if(libraryItems[j].getArtistName().equals(String.valueOf(viewHolder.txtViewArtist.getText()))) {
                        albumList.add(libraryItems[j].getAlbumTitle());
                    }
                }

                Set<String> hsSongsList = new HashSet<>();
                hsSongsList.addAll(albumList);
                albumList.clear();
                albumList.addAll(hsSongsList);

                Collections.sort(albumList, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareToIgnoreCase(o2);
                    }
                });

                Intent browseArtistAlbums = new Intent(view.getContext(), Artist.class);
                browseArtistAlbums.putExtra(EXTRA_ARTIST, String.valueOf(viewHolder.txtViewArtist.getText()));
                browseArtistAlbums.putExtra(EXTRA_ALBUMLIST, albumList);
                browseArtistAlbums.putExtra(EXTRA_WHOSCALLING,"library");
                context.startActivity(browseArtistAlbums);
            }
        });
    }

    // Return the size of your libraryItems (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ArtistsList.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewArtist;
        private ImageView imgViewIcon;

        private ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewArtist = (TextView) itemLayoutView.findViewById(R.id.library_field);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.library_thumb);
        }
    }
}

