package com.example.android.mymusicapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUM;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ALBUMLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_ARTIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_COVER;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONG;
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
    private String song;
    private int albumCoverID;

    public LibraryItemsSongsAdapter(ArrayList<String> SongsList, @Nullable ArrayList<String> AlbumsList,
                                    @Nullable String Album, @Nullable String Artist, Context context,
                                    String identifier) {
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
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        viewHolder.txtViewSong.setText(SongsList.get(position));

        int i = 0;

        while (i < libraryItems.length) {
            if (libraryItems[i].getImageForSong(SongsList.get(position)) == -1) {
                viewHolder.imgViewIcon.setImageResource(R.drawable.music_note);
                break;
            }
            else if (libraryItems[i].getImageForSong(SongsList.get(position)) == 0) {
                viewHolder.imgViewIcon.setImageResource(R.drawable.music_note);
                i++;
            } else {
                viewHolder.imgViewIcon.setImageResource(libraryItems[i].getImageID());
                break;
            }
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Log.i("POS_CLICKED: ", String.valueOf(viewHolder.getAdapterPosition()));

                int j=0;
                int pos = viewHolder.getAdapterPosition();
                song = SongsList.get(pos);

                if (activityID.equals("album")) {

                    while (j < libraryItems.length) {
                        if (libraryItems[j].getImageForSong(song) == -1) {
                            albumCoverID = R.drawable.music_note;
                            break;
                        }
                        else if (libraryItems[j].getImageForSong(song) == 0) {
                            albumCoverID = R.drawable.music_note;
                            j++;
                        } else {
                            albumCoverID = libraryItems[j].getImageForSong(song);
                            break;
                        }
                    }

                    Intent playFromAlbum = new Intent(v.getContext(), PlayAlbumTrack.class);
                    playFromAlbum.putExtra(EXTRA_ALBUM,Album);
                    playFromAlbum.putExtra(EXTRA_SONGLIST,SongsList);
                    playFromAlbum.putExtra(EXTRA_ARTIST,Artist);
                    playFromAlbum.putExtra(EXTRA_ALBUMLIST,AlbumsList);
                    playFromAlbum.putExtra(EXTRA_SONG, song);
                    playFromAlbum.putExtra(EXTRA_COVER, albumCoverID);
                    playFromAlbum.putExtra(EXTRA_WHOSCALLING,"album");
                    v.getContext().startActivity(playFromAlbum);

                } else if (activityID.equals("artistalbum")) {

                    while (j < libraryItems.length) {
                        if (libraryItems[j].getImageForSong(song) == -1) {
                            albumCoverID = R.drawable.music_note;
                            break;
                        }
                        else if (libraryItems[j].getImageForSong(song) == 0) {
                            albumCoverID = R.drawable.music_note;
                            j++;
                        } else {
                            albumCoverID = libraryItems[j].getImageForSong(song);
                            break;
                        }
                    }

                    Intent playFromAlbum = new Intent(v.getContext(), PlayAlbumTrack.class);
                    playFromAlbum.putExtra(EXTRA_ALBUM,Album);
                    playFromAlbum.putExtra(EXTRA_SONGLIST,SongsList);
                    playFromAlbum.putExtra(EXTRA_ARTIST,Artist);
                    playFromAlbum.putExtra(EXTRA_ALBUMLIST,AlbumsList);
                    playFromAlbum.putExtra(EXTRA_SONG, song);
                    playFromAlbum.putExtra(EXTRA_COVER, albumCoverID);
                    playFromAlbum.putExtra(EXTRA_WHOSCALLING,"artistalbum");
                    v.getContext().startActivity(playFromAlbum);

                } else if (activityID.equals("library")) {

                    while (j < libraryItems.length) {
                        if (libraryItems[j].getImageForSong(song) == -1) {
                            albumCoverID = R.drawable.music_note;
                            Artist = libraryItems[j].getArtistName();
                            Album = libraryItems[j].getAlbumTitle();
                            break;
                        }
                        else if (libraryItems[j].getImageForSong(song) == 0) {
                            albumCoverID = R.drawable.music_note;
                            j++;
                        } else {
                            albumCoverID = libraryItems[j].getImageForSong(song);
                            Artist = libraryItems[j].getArtistName();
                            Album = libraryItems[j].getAlbumTitle();
                            break;
                        }
                    }

                    Intent playFromSongList = new Intent(v.getContext(), PlaySingle.class);
                    playFromSongList.putExtra(EXTRA_ALBUM,Album);
                    playFromSongList.putExtra(EXTRA_ARTIST,Artist);
                    playFromSongList.putExtra(EXTRA_SONG, song);
                    playFromSongList.putExtra(EXTRA_COVER, albumCoverID);
                    playFromSongList.putExtra(EXTRA_WHOSCALLING,"album");
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

