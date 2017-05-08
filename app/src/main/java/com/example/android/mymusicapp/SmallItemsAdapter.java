package com.example.android.mymusicapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class SmallItemsAdapter extends RecyclerView.Adapter<SmallItemsAdapter.ViewHolder> {

    private SmallItemsData[] itemsData;
    private boolean enable; // it decides whether or not to enable the OnClickListener

    public SmallItemsAdapter(SmallItemsData[] itemsData, boolean enable) {
        this.itemsData = itemsData;
        this.enable = enable;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SmallItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.small_items, parent, false);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        viewHolder.txtViewTitle.setText(itemsData[position].getTitle());
        viewHolder.txtViewArtist.setText(itemsData[position].getArtist());
        viewHolder.imgViewIcon.setImageResource(itemsData[position].getImageID());

        viewHolder.imgViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // OnClickListener enabled ONLY FOR "Recently Purchased" items
                // i.e. albums that already exist in the library
                if (enable) {

                    // Create a list of all the songs in the album
                    ArrayList<String> songList = new ArrayList<>();
                    for (int i = 0; i < libraryItems.length; i++) {
                        if (libraryItems[i].getAlbumTitle().equals
                                (itemsData[viewHolder.getAdapterPosition()].getTitle()))
                            songList.add(libraryItems[i].getSongTitle());
                    }

                    // Discard repetitions
                    Set<String> hsSongsList = new HashSet<>();
                    hsSongsList.addAll(songList);
                    songList.clear();
                    songList.addAll(hsSongsList);

                    // Sort by alphabetical order
                    Collections.sort(songList, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o1.compareToIgnoreCase(o2);
                        }
                    });


                    Intent showThisItem = new Intent(v.getContext(), Album.class);
                    showThisItem.putExtra(EXTRA_ARTIST,
                            itemsData[viewHolder.getAdapterPosition()].getArtist());
                    showThisItem.putExtra(EXTRA_ALBUM,
                            itemsData[viewHolder.getAdapterPosition()].getTitle());
                    showThisItem.putExtra(EXTRA_SONGLIST, songList);
                    showThisItem.putExtra(EXTRA_WHOSCALLING, "main");
                    v.getContext().startActivity(showThisItem);

                }
            }
        });

    }

    // Inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewTitle;
        private TextView txtViewArtist;
        private ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.album_title);
            txtViewArtist = (TextView) itemLayoutView.findViewById(R.id.album_artist);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.album_image);
        }
    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemsData.length;
    }
}
