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


import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUM;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUMLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ARTIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONGLIST;
import static com.example.android.mymusicapp.MainActivity.libraryItems;

public class LibraryItemsAlbumsAdapter
        extends RecyclerView.Adapter<LibraryItemsAlbumsAdapter.ViewHolder> {
    
    private ArrayList<String> AlbumsList;
    private Context context;
    private String activityID;

    public LibraryItemsAlbumsAdapter(ArrayList<String> AlbumsList, Context context, String identifier) {
        this.AlbumsList = AlbumsList;
        this.context = context;
        this.activityID = identifier;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LibraryItemsAlbumsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

        viewHolder.txtViewAlbum.setText(AlbumsList.get(position));

        int i = 0;

        while (i < libraryItems.length) {

            if (libraryItems[i].getImageForAlbum(AlbumsList.get(position)) == 0) {
                viewHolder.imgViewIcon.setImageResource(R.mipmap.ic_music_note_black_24dp);
                i++;
            } else if (libraryItems[i].getImageForAlbum(AlbumsList.get(position)) == -1) {
                viewHolder.imgViewIcon.setImageResource(R.mipmap.ic_music_note_black_24dp);
                break;
            }
            else {
                viewHolder.imgViewIcon.setImageResource(libraryItems[i].getImageID());
                break;
            }
        }

        final ArrayList<String> songList = new ArrayList<>();
        final int numberOfItems = libraryItems.length;

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            String albumArtist;

            @Override
            public void onClick (View view){
                for (int j = 0; j < numberOfItems; j++) {

                    if(libraryItems[j].getAlbumTitle().equals(String.valueOf(viewHolder.txtViewAlbum.getText()))) {
                        songList.add(libraryItems[j].getSongTitle());
                        albumArtist=libraryItems[j].getArtistName();
                    }
                }

                Collections.sort(songList, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareToIgnoreCase(o2);
                    }
                });


                if (activityID.equals("library")) {
                    Intent browseAlbumSongs = new Intent(view.getContext(), Album.class);
                    browseAlbumSongs.putExtra(EXTRA_ALBUM, String.valueOf(viewHolder.txtViewAlbum.getText()));
                    browseAlbumSongs.putExtra(EXTRA_SONGLIST, songList);
                    browseAlbumSongs.putExtra(EXTRA_ARTIST, albumArtist);
                    browseAlbumSongs.putExtra(EXTRA_ALBUMLIST, AlbumsList);
                    context.startActivity(browseAlbumSongs);
                }

                else if (activityID.equals("artistAlbum")) {
                    Intent browseAlbumSongs = new Intent(view.getContext(), AlbumFromArtist.class);
                    browseAlbumSongs.putExtra(EXTRA_ALBUM, String.valueOf(viewHolder.txtViewAlbum.getText()));
                    browseAlbumSongs.putExtra(EXTRA_SONGLIST, songList);
                    browseAlbumSongs.putExtra(EXTRA_ARTIST, albumArtist);
                    browseAlbumSongs.putExtra(EXTRA_ALBUMLIST, AlbumsList);
                    context.startActivity(browseAlbumSongs);
                }
            }
        });

    }

    // Return the size of your libraryItems (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return AlbumsList.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewAlbum;
        private ImageView imgViewIcon;

        private ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewAlbum = (TextView) itemLayoutView.findViewById(R.id.library_field);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.library_thumb);
        }
    }
}

