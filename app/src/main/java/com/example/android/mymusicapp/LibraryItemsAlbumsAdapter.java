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

import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUM;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUMLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ARTIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONGLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_WHOSCALLING;
import static com.example.android.mymusicapp.MainActivity.libraryItems;

public class LibraryItemsAlbumsAdapter
        extends RecyclerView.Adapter<LibraryItemsAlbumsAdapter.ViewHolder> {

    private ArrayList<String> albumList;
    private String activityID;

    /*
       The String "identifier" identifies whether we are visualizing the album list
       within the tab fragment "Albums" in the activity "MyLibrary" (all albums), or
       within the activity "Artist" (only albums by a specific artist).
       According to this, we will have a different OnClick behaviour (see method
       "onBindViewHolder" below)
    */
    public LibraryItemsAlbumsAdapter(ArrayList<String> albumList, String identifier) {
        this.albumList = albumList;
        this.activityID = identifier;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LibraryItemsAlbumsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

        // Set album title for the item at the current position
        viewHolder.txtViewAlbum.setText(albumList.get(position));

        /*
           Set thumbnail for the item at the current position: find the first library item
           that matches the album title and take its image as the thumbnail. If method
           getImageForAlbum returns -1, it means that there is no image available.
        */
        int i = 0;
        while (i < libraryItems.length) {

            if (libraryItems[i].getImageForAlbum(albumList.get(position)) == 0) {
                viewHolder.imgViewIcon.setImageResource(R.drawable.music_note);
                i++;
            } else if (libraryItems[i].getImageForAlbum(albumList.get(position)) == -1) {
                viewHolder.imgViewIcon.setImageResource(R.drawable.music_note);
                break;
            } else {
                viewHolder.imgViewIcon.setImageResource(libraryItems[i].getImageID());
                break;
            }
        }

        /*
           When one of the items (albums) is clicked, an intent is sent to the activity "Album" or
           "AlbumFromArtist", according to whether we are currently visualizing the album list
           within the tab fragment "Albums" in the activity "MyLibrary" (all albums), or
           within the activity "Artist" (only albums by a specific artist).
           In the latter case, the variable "albumList" (the list of albums by the selected artist)
           must be passed on to "AlbumFromArtist" via extra attachment (EXTRA_ALBUMLIST).
           In both cases, we need to pass EXTRA_ALBUM (the clicked item),
           EXTRA_SONGLIST (all the songs in the clicked album),
           EXTRA_ARTIST (the artist of the clicked album).
         */
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            String albumArtist;
            ArrayList<String> songList = new ArrayList<>();

            @Override
            public void onClick(View view) {

                /*
                   Determine the artist and the list of songs for the clicked album
                   scanning through the library items looking for a title match.
                   Every time there is a match, call getSongTitle() on the library item
                   and add the output string to the ArrayList<String> "songList".
                   To get the artist name, find the first library item that matches the album title
                   and call getArtistName().
                */
                for (int j = 0; j < libraryItems.length; j++) {

                    if (libraryItems[j].getAlbumTitle().equals
                            (albumList.get(viewHolder.getAdapterPosition()))) {
                        songList.add(libraryItems[j].getSongTitle());
                        albumArtist = libraryItems[j].getArtistName();
                    }
                }

                // Avoid repetitions in the song list
                Set<String> hsSongList = new HashSet<>();
                hsSongList.addAll(songList);
                songList.clear();
                songList.addAll(hsSongList);

                // Sort in alphabetical order
                Collections.sort(songList, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareToIgnoreCase(o2);
                    }
                });


                if (activityID.equals("library")) {
                    Intent browseAlbumSongs = new Intent(view.getContext(), Album.class);
                    browseAlbumSongs.putExtra(EXTRA_ALBUM,
                            albumList.get(viewHolder.getAdapterPosition()));
                    browseAlbumSongs.putExtra(EXTRA_SONGLIST, songList);
                    browseAlbumSongs.putExtra(EXTRA_ARTIST, albumArtist);
                    browseAlbumSongs.putExtra(EXTRA_WHOSCALLING, "library");
                    view.getContext().startActivity(browseAlbumSongs);

                } else if (activityID.equals("artist")) {
                    Intent browseAlbumSongs = new Intent(view.getContext(), AlbumFromArtist.class);
                    browseAlbumSongs.putExtra(EXTRA_ALBUM,
                            albumList.get(viewHolder.getAdapterPosition()));
                    browseAlbumSongs.putExtra(EXTRA_SONGLIST, songList);
                    browseAlbumSongs.putExtra(EXTRA_ARTIST, albumArtist);
                    browseAlbumSongs.putExtra(EXTRA_ALBUMLIST, albumList);
                    browseAlbumSongs.putExtra(EXTRA_WHOSCALLING, "artist");
                    view.getContext().startActivity(browseAlbumSongs);
                }
            }
        });

    }

    // Return the size of your libraryItems (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return albumList.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewAlbum;
        private ImageView imgViewIcon;

        private ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            // Album title
            txtViewAlbum = (TextView) itemLayoutView.findViewById(R.id.library_txtView);
            // Album thumbnail
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.library_thumb);
        }
    }
}

