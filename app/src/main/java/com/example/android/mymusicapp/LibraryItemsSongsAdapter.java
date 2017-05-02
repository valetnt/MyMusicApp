package com.example.android.mymusicapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUM;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUMLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ARTIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONGLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_WHOSCALLING;
import static com.example.android.mymusicapp.MainActivity.libraryItems;

public class LibraryItemsSongsAdapter
        extends RecyclerView.Adapter<LibraryItemsSongsAdapter.ViewHolder> {
    
    private ArrayList<String> SongsList;
    private ArrayList<String> AlbumsList;
    private Context context;
    private String activityID;
    private String Artist;
    private String Album;

    public LibraryItemsSongsAdapter(ArrayList<String> SongsList, @Nullable ArrayList<String> AlbumsList,
                                    @Nullable String Album, @Nullable String Artist, Context context, String identifier) {
        this.SongsList = SongsList;
        this.AlbumsList = AlbumsList;
        this.Album = Album;
        this.Artist = Artist;
        this.context = context;
        activityID = identifier;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LibraryItemsSongsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.library_list_items, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.txtViewSong.setText(SongsList.get(position));

        int i = 0;

        while (i < libraryItems.length) {
            if (libraryItems[i].getImageForSong(SongsList.get(position)) == -1) {
                viewHolder.imgViewIcon.setImageResource(R.mipmap.ic_music_note_black_24dp);
                break;
            }
            else if (libraryItems[i].getImageForSong(SongsList.get(position)) == 0) {
                viewHolder.imgViewIcon.setImageResource(R.mipmap.ic_music_note_black_24dp);
                i++;
            } else {
                viewHolder.imgViewIcon.setImageResource(libraryItems[i].getImageID());
                break;
            }
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityID.equals("album")) {

                    Intent playFromAlbum = new Intent(v.getContext(), PlayAlbumTrack.class);
                    playFromAlbum.putExtra(EXTRA_ALBUM,Album);
                    playFromAlbum.putExtra(EXTRA_SONGLIST,SongsList);
                    playFromAlbum.putExtra(EXTRA_ARTIST,Artist);
                    playFromAlbum.putExtra(EXTRA_WHOSCALLING,"album");
                    v.getContext().startActivity(playFromAlbum);

                } else if (activityID.equals("artistalbum")) {

                    Intent playFromAlbum = new Intent(v.getContext(), PlayAlbumTrack.class);
                    playFromAlbum.putExtra(EXTRA_ALBUM,Album);
                    playFromAlbum.putExtra(EXTRA_SONGLIST,SongsList);
                    playFromAlbum.putExtra(EXTRA_ARTIST,Artist);
                    playFromAlbum.putExtra(EXTRA_ALBUMLIST,AlbumsList);
                    playFromAlbum.putExtra(EXTRA_WHOSCALLING,"artistalbum");
                    v.getContext().startActivity(playFromAlbum);

                } else if (activityID.equals("library")) {

                    Intent playFromSongList = new Intent(v.getContext(), PlaySingle.class);
                    v.getContext().startActivity(playFromSongList);
                }
            }
        });


    }

    // Return the size of your libraryItems (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return SongsList.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewSong;
        private ImageView imgViewIcon;

        private ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewSong = (TextView) itemLayoutView.findViewById(R.id.library_field);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.library_thumb);
        }
    }
}

