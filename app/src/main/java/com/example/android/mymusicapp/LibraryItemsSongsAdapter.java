package com.example.android.mymusicapp;

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
import static com.example.android.mymusicapp.MainActivity.EXTRA_COVER;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONG;
import static com.example.android.mymusicapp.MainActivity.EXTRA_SONGLIST;
import static com.example.android.mymusicapp.MainActivity.EXTRA_WHOSCALLING;
import static com.example.android.mymusicapp.MainActivity.libraryItems;

public class LibraryItemsSongsAdapter
        extends RecyclerView.Adapter<LibraryItemsSongsAdapter.ViewHolder> {

    private ArrayList<String> songList;
    private ArrayList<String> albumList;
    private String activityID;
    private String artist;
    private String album;

    /*
       The String "identifier" identifies whether we are visualizing the song list
       within the tab fragment "All" in the activity "MyLibrary" (all songs), or
       within the activity "Album" (only songs from a specific album) or
       within the activity "AlbumFromArtist" (only songs contained in a specific album
       by a specific artist). According to this, we will have a different OnClick behaviour
       (see method "onBindViewHolder" below).
    */
    public LibraryItemsSongsAdapter(ArrayList<String> songList,
                                    @Nullable ArrayList<String> albumList, @Nullable String album,
                                    @Nullable String artist, String identifier) {
        this.songList = songList;
        this.albumList = albumList;
        this.album = album;
        this.artist = artist;
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

        // Set song title for the item at the current position
        viewHolder.txtViewSong.setText(songList.get(position));

        /*
           Set thumbnail for the item at the current position: find the first library item
           that matches the song title and take its image as the thumbnail. If method
           getImageForSong returns -1, it means that there is no image available.
        */
        int i = 0;
        while (i < libraryItems.length) {
            if (libraryItems[i].getImageForSong(songList.get(position)) == -1) {
                viewHolder.imgViewIcon.setImageResource(R.drawable.music_note);
                break;
            } else if (libraryItems[i].getImageForSong(songList.get(position)) == 0) {
                viewHolder.imgViewIcon.setImageResource(R.drawable.music_note);
                i++;
            } else {
                viewHolder.imgViewIcon.setImageResource(libraryItems[i].getImageID());
                break;
            }
        }

        /*
           When one of the items (songs) is clicked, an intent is sent
           to the activity "PlaySingle" or to the activity "PlayAlbumTrack",
           according to whether we are currently visualizing the song list
           within the tab fragment "All" in the activity "MyLibrary" (all songs),
           or within the activity "Album" or "AlbumFromArtist" (only songs from a specific album).
           In the first case, the variables that must be attached to the intent in order to
           be passed on to the activity "PlaySingle" are:

           EXTRA_SONG (the clicked item),
           EXTRA_ALBUM (the album to which the song belongs, to be displayed as song info/details),
           EXTRA_COVER (the album cover to be displayed as song info/details)
           EXTRA_ARTIST (the artist name to be displayed as song info/details).

           In case the intent is being sent from the activity "Album", besides the 4 extras
           mentioned above, another one must be passed on to "PlayAlbumTrack":

           EXTRA_SONGLIST (the list of songs contained in the same album as the clicked song)

           And if the intent is being sent from "AlbumFromArtist", then another one too:

           EXTRA_ALBUMLIST (the list of albums by the same artist as the clicked song)

           The passing of these extras to "PlayAlbumTrack" guarantees that it is possible to use
           the buttons "Back To Album" and "Back To Artist", for better browsing experience.
         */

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            String song = songList.get(viewHolder.getAdapterPosition());
            int albumCoverID;

            @Override
            public void onClick(View v) {

                int j = 0;
                if (activityID.equals("album")) {

                    /*
                       Determine the album/single cover for the clicked song scanning through
                       the library items looking for a title match. If method getImageForSong
                       returns -1, it means that there is no image available for this song.
                    */
                    while (j < libraryItems.length) {
                        if (libraryItems[j].getImageForSong(song) == -1) {
                            albumCoverID = R.drawable.music_note;
                            break;
                        } else if (libraryItems[j].getImageForSong(song) == 0) {
                            albumCoverID = R.drawable.music_note;
                            j++;
                        } else {
                            albumCoverID = libraryItems[j].getImageForSong(song);
                            break;
                        }
                    }

                    Intent playFromAlbum = new Intent(v.getContext(), PlayAlbumTrack.class);
                    playFromAlbum.putExtra(EXTRA_ALBUM, album);
                    playFromAlbum.putExtra(EXTRA_SONGLIST, songList);
                    playFromAlbum.putExtra(EXTRA_ARTIST, artist);
                    playFromAlbum.putExtra(EXTRA_SONG, song);
                    playFromAlbum.putExtra(EXTRA_COVER, albumCoverID);
                    playFromAlbum.putExtra(EXTRA_WHOSCALLING, "album");
                    v.getContext().startActivity(playFromAlbum);

                } else if (activityID.equals("artistalbum")) {

                    /*
                       Determine the album/single cover for the clicked song scanning through
                       the library items looking for a title match. If method getImageForSong
                       returns -1, it means that there is no image available for this song.
                    */
                    while (j < libraryItems.length) {
                        if (libraryItems[j].getImageForSong(song) == -1) {
                            albumCoverID = R.drawable.music_note;
                            break;
                        } else if (libraryItems[j].getImageForSong(song) == 0) {
                            albumCoverID = R.drawable.music_note;
                            j++;
                        } else {
                            albumCoverID = libraryItems[j].getImageForSong(song);
                            break;
                        }
                    }

                    Intent playFromAlbum = new Intent(v.getContext(), PlayAlbumTrack.class);
                    playFromAlbum.putExtra(EXTRA_ALBUM, album);
                    playFromAlbum.putExtra(EXTRA_SONGLIST, songList);
                    playFromAlbum.putExtra(EXTRA_ARTIST, artist);
                    playFromAlbum.putExtra(EXTRA_ALBUMLIST, albumList);
                    playFromAlbum.putExtra(EXTRA_SONG, song);
                    playFromAlbum.putExtra(EXTRA_COVER, albumCoverID);
                    playFromAlbum.putExtra(EXTRA_WHOSCALLING, "artistalbum");
                    v.getContext().startActivity(playFromAlbum);

                } else if (activityID.equals("library")) {

                    /*
                       Determine the album/single cover, the artist name and the album/single
                       title for the clicked song scanning through the library items
                       looking for a title match. Every time there is a match, call methods
                       getImageForSong(song), getArtistName() and getAlbumTitle()
                       on the library item. If method getImageForSong(song) returns -1,
                       it means that there is no image available for this song.
                    */
                    while (j < libraryItems.length) {
                        if (libraryItems[j].getImageForSong(song) == -1) {
                            albumCoverID = R.drawable.music_note;
                            artist = libraryItems[j].getArtistName();
                            album = libraryItems[j].getAlbumTitle();
                            break;
                        } else if (libraryItems[j].getImageForSong(song) == 0) {
                            albumCoverID = R.drawable.music_note;
                            j++;
                        } else {
                            albumCoverID = libraryItems[j].getImageForSong(song);
                            artist = libraryItems[j].getArtistName();
                            album = libraryItems[j].getAlbumTitle();
                            break;
                        }
                    }

                    Intent playFromSongList = new Intent(v.getContext(), PlaySingle.class);
                    playFromSongList.putExtra(EXTRA_ALBUM, album);
                    playFromSongList.putExtra(EXTRA_ARTIST, artist);
                    playFromSongList.putExtra(EXTRA_SONG, song);
                    playFromSongList.putExtra(EXTRA_COVER, albumCoverID);
                    playFromSongList.putExtra(EXTRA_WHOSCALLING, "library");
                    v.getContext().startActivity(playFromSongList);
                }
            }
        });
    }

    // Return the size of your libraryItems (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return songList.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewSong;
        private ImageView imgViewIcon;

        private ViewHolder(View itemLayoutView) {

            super(itemLayoutView);
            // Song title
            txtViewSong = (TextView) itemLayoutView.findViewById(R.id.library_txtView);
            // Song thumbnail
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.library_thumb);
        }
    }
}

